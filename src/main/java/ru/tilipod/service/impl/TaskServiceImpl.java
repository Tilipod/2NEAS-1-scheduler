package ru.tilipod.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.exception.EntityNotFoundException;
import ru.tilipod.exception.InvalidDataException;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;
import ru.tilipod.jpa.repository.TaskRepository;
import ru.tilipod.service.NeuronNetworkService;
import ru.tilipod.service.TaskService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ObjectMapper objectMapper;

    private final NeuronNetworkService neuronNetworkService;

    @Override
    @Transactional(readOnly = true)
    public TaskStatusEnum getTaskStatusByProcessId(UUID processId) {
        return taskRepository.findByProcessId(processId)
                .map(Task::getStatus)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Сущность с ID = %s не найдена.",processId), Task.class));
    }

    @Override
    @Transactional
    public UUID createNewTask(TrainingRequestDto request) {
        Task task = new Task();

        task.setWithMentoring(request.getWithMentoring());
        try {
            task.setJsonClient(objectMapper.writeValueAsString(request.getLayers()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new InvalidDataException("Ошибка в данных запроса.", request.getLayers());
        }
        task.setStatus(TaskStatusEnum.CREATED);
        task.setProcessId(UUID.randomUUID());

        task = taskRepository.save(task);

        neuronNetworkService.createNewNetwork(task, request.getNeuronNetworkType());

        return task.getProcessId();
    }

}

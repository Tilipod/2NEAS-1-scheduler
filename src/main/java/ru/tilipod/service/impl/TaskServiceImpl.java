package ru.tilipod.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.controller.dto.TrainingResponseDto;
import ru.tilipod.event.TaskStatusChangeEvent;
import ru.tilipod.exception.EntityNotFoundException;
import ru.tilipod.exception.InvalidDataException;
import ru.tilipod.exception.SystemError;
import ru.tilipod.jpa.entity.NeuronNetwork;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;
import ru.tilipod.jpa.repository.TaskRepository;
import ru.tilipod.service.CourceService;
import ru.tilipod.service.DistributionService;
import ru.tilipod.service.NeuronNetworkService;
import ru.tilipod.service.TaskService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ObjectMapper objectMapper;

    private final ApplicationEventPublisher eventPublisher;

    private final DistributionService distributionService;

    private final NeuronNetworkService neuronNetworkService;

    private final CourceService courceService;

    @Override
    @Transactional(readOnly = true)
    public TaskStatusEnum getTaskStatusByProcessId(UUID processId) {
        return taskRepository.findByProcessId(processId)
                .map(Task::getStatus)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Сущность с ID = %s не найдена.",processId), Task.class));
    }

    @Override
    @Transactional(readOnly = true)
    public TrainingResponseDto getTaskTrainingResult(UUID processId) {
        Task task = findByProcessId(processId);
        NeuronNetwork net = neuronNetworkService.findByTaskProcessId(processId);
        TrainingResponseDto response = new TrainingResponseDto();

        response.setStatus(task.getStatus());
        response.setComment(task.getComment());

        if (TaskStatusEnum.TRAINED.equals(response.getStatus())
            || TaskStatusEnum.CONFIRMED.equals(response.getStatus())) {
            try (InputStream in = new FileInputStream(net.getPathToModel())) {
                response.setModel(in.readAllBytes());
            } catch (Exception e) {
                log.error("Ошибка чтения обученной модели по задаче {}. Ошибка: {}", task.getId(), e.getMessage());
                throw new SystemError("Ошибка чтения обученной модели. Пожалуйста, обратитесь к разработчику");
            }
        }

        return response;
    }

    @Override
    @Transactional
    public UUID createNewTask(TrainingRequestDto request) {
        Task task = new Task();

        try {
            task.setJsonNetworkStructure(objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new InvalidDataException("Ошибка в данных запроса.", request);
        }
        task.setStatus(TaskStatusEnum.CREATED);
        task.setProcessId(UUID.randomUUID());

        task = taskRepository.save(task);

        distributionService.createNewFromClientRequest(request, task);
        NeuronNetwork net = neuronNetworkService.createNetworkFromClientRequest(request, task);
        courceService.createCourseFromClientRequest(request, net);

        return task.getProcessId();
    }

    @Override
    @Transactional
    public Task changeStatus(Task task, TaskStatusEnum newStatus) {
        log.info("Изменение статуса задачи {} с {} на {}", task.getId(), task.getStatus(), newStatus);

        task.setStatus(newStatus);
        task = taskRepository.save(task);

        eventPublisher.publishEvent(new TaskStatusChangeEvent(this, task, newStatus));

        return task;
    }

    @Override
    @Transactional(readOnly = true)
    public Task findByProcessId(UUID processId) {
        return taskRepository.findByProcessId(processId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Задача с processId = %s не найдена.", processId),
                        Task.class));
    }

    @Override
    @Transactional
    public boolean stopTask(UUID processId) {
        Task task = findByProcessId(processId);

        try {
            changeStatus(task, TaskStatusEnum.STOPPED);
        } catch (Exception e) {
            log.error("Неудачная остановка задачи {}. Причина: {}", task.getId(), e.getMessage());
            return false;
        }

        log.info("Задача {} остановлена пользователем", task.getId());
        return true;
    }

    @Override
    @Transactional
    public boolean redistribute(UUID processId) {
        Task task = findByProcessId(processId);

        try {
            changeStatus(task, TaskStatusEnum.ANALYZED);
        } catch (Exception e) {
            log.error("Неудачная редистрибьюция задачи {}. Причина: {}", task.getId(), e.getMessage());
            return false;
        }

        log.info("Задача {} отправлена пользователем на обновление датасетов", task.getId());
        return true;
    }

}

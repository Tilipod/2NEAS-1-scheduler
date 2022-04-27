package ru.tilipod.service;

import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.controller.dto.TrainingResponseDto;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

public interface TaskService {

    TrainingResponseDto getTaskStatusByProcessId(UUID processId);

    byte[] getTaskTrainingResult(UUID processId);

    UUID createNewTask(TrainingRequestDto request);

    Task findByProcessId(UUID processId);

    Task findById(Integer taskId);

    List<Task> findAllByStatus(TaskStatusEnum status);

    int prepareAndSendToParser(Task task);

    int prepareAndSendToDistributor(Task task);

    int prepareAndSendToTeacher(Task task);

    Task changeStatus(Task task, TaskStatusEnum newStatus, String comment);

    boolean stopTask(UUID taskId);

    boolean redistribute(UUID taskId);

}

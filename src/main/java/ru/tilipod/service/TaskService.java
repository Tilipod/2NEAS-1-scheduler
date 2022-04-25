package ru.tilipod.service;

import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.controller.dto.TrainingResponseDto;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;

import java.util.UUID;

public interface TaskService {

    TaskStatusEnum getTaskStatusByProcessId(UUID processId);

    TrainingResponseDto getTaskTrainingResult(UUID processId);

    UUID createNewTask(TrainingRequestDto request);

    Task findByProcessId(UUID processId);

    Task changeStatus(Task task, TaskStatusEnum newStatus);

    boolean stopTask(UUID taskId);

    boolean redistribute(UUID taskId);

}

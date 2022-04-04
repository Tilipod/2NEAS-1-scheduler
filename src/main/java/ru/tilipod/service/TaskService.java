package ru.tilipod.service;

import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;

import java.util.UUID;

public interface TaskService {

    TaskStatusEnum getTaskStatusByProcessId(UUID processId);

    UUID createNewTask(TrainingRequestDto request);

}

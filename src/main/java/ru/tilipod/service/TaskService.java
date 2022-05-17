package ru.tilipod.service;

import ru.tilipod.controller.dto.TaskStatusChangeDto;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.controller.dto.TrainingResponseDto;
import ru.tilipod.controller.dto.TrainingStatisticDto;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;

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

    void changeStatus(UUID taskId, TaskStatusChangeDto statusChangeDto);

    boolean stopTask(UUID taskId);

    TrainingStatisticDto getStatistic(UUID taskId);

}

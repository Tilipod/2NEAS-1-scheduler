package ru.tilipod.service;

import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.jpa.entity.Distribution;
import ru.tilipod.jpa.entity.Task;

import java.util.UUID;

public interface DistributionService {

    Distribution createNewFromClientRequest(TrainingRequestDto request, Task task);

    Distribution findByTaskId(Integer taskId);

    Distribution findByTaskProcessId(UUID processId);
}
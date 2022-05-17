package ru.tilipod.service;

import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.jpa.entity.nneas.Distribution;
import ru.tilipod.jpa.entity.nneas.Task;

import java.util.UUID;

public interface DistributionService {

    Distribution createNewFromClientRequest(TrainingRequestDto request, Task task);

    Distribution findByTaskId(Integer taskId);

    Distribution findByTaskProcessId(UUID processId);

    Distribution update(Distribution distribution);
}

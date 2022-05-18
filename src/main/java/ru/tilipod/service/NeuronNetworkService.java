package ru.tilipod.service;

import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.jpa.entity.nneas.NeuronNetwork;
import ru.tilipod.jpa.entity.nneas.Task;

import java.util.UUID;

public interface NeuronNetworkService {

    NeuronNetwork createNetworkFromClientRequest(TrainingRequestDto request, Task task);

    NeuronNetwork findByTaskId(Integer taskId);

    NeuronNetwork findByTaskProcessId(UUID processId);

    NeuronNetwork updateNetwork(NeuronNetwork nn);

}

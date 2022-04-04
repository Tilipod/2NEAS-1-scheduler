package ru.tilipod.service;

import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.NeuronNetworkType;

public interface NeuronNetworkService {

    void createNewNetwork(Task task, NeuronNetworkType type);

}

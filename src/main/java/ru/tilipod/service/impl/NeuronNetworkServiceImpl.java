package ru.tilipod.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.jpa.entity.NeuronNetwork;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.NeuronNetworkType;
import ru.tilipod.jpa.repository.NeuronNetworkRepository;
import ru.tilipod.service.NeuronNetworkService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NeuronNetworkServiceImpl implements NeuronNetworkService {

    private final NeuronNetworkRepository neuronNetworkRepository;

    @Override
    @Transactional
    public void createNewNetwork(Task task, NeuronNetworkType type) {
        NeuronNetwork network = new NeuronNetwork();

        network.setTask(task);
        network.setType(type);

        neuronNetworkRepository.save(network);
    }

}

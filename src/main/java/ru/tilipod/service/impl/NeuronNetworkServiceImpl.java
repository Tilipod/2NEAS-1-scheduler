package ru.tilipod.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.controller.dto.parser.LayerDto;
import ru.tilipod.exception.InvalidDataException;
import ru.tilipod.jpa.entity.NeuronNetwork;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.repository.NeuronNetworkRepository;
import ru.tilipod.service.NeuronNetworkService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NeuronNetworkServiceImpl implements NeuronNetworkService {

    private final NeuronNetworkRepository neuronNetworkRepository;

    @Value("${commonPathToModel}")
    private String commonPathToModel;

    @Value("${pathPattern}")
    private String pathPattern;

    @Override
    @Transactional
    public NeuronNetwork createNetworkFromClientRequest(TrainingRequestDto request, Task task) {
        NeuronNetwork network = new NeuronNetwork();

        String pathToModel = commonPathToModel.concat(String.format(pathPattern, task.getId(), UUID.randomUUID()));
        network.setTask(task);
        if (request.getWithMentoring() != null) {
            network.setWithMentoring(request.getWithMentoring());
        }
        network.setCountOutputs(searchCountOutputs(request.getNeuronNetworkStructure().getLayers()));
        network.setPathToModel(pathToModel);

        network = neuronNetworkRepository.save(network);

        log.info("По задаче {} в БД добавлена информация по нейронной сети. Зарезервированный путь к модели: {}",
                task.getId(), pathToModel);

        return network;
    }

    @Override
    @Transactional(readOnly = true)
    public NeuronNetwork findByTaskId(Integer taskId) {
        return neuronNetworkRepository.findByTask_Id(taskId)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public NeuronNetwork findByTaskProcessId(UUID processId) {
        return neuronNetworkRepository.findByTask_ProcessId(processId)
                .orElse(null);
    }

    private Integer searchCountOutputs(List<LayerDto> layers) {
        return layers.stream()
                .filter(l -> LayerDto.LayerTypeEnum.OUTPUT.equals(l.getLayerType()))
                .findFirst()
                .map(LayerDto::getCountOutput)
                .orElseThrow(() -> new InvalidDataException("Не найден выходной слой нейросети.", layers));
    }

}

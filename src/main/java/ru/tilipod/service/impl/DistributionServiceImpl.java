package ru.tilipod.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.jpa.entity.Distribution;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.repository.DistributionRepository;
import ru.tilipod.service.DistributionService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistributionServiceImpl implements DistributionService {

    private final DistributionRepository distributionRepository;

    @Value("${commonPathToDataset}")
    private String commonPathToDataset;

    @Value("${pathPattern}")
    private String pathPattern;

    @Override
    @Transactional
    public Distribution createNewFromClientRequest(TrainingRequestDto request, Task task) {
        Distribution distribution = new Distribution();

        String pathToDataset = commonPathToDataset.concat(String.format(pathPattern, task.getId(), UUID.randomUUID()));
        distribution.setTask(task);
        distribution.setCloudToken(request.getCloudToken());
        distribution.setCloudType(request.getCloudType());
        distribution.setDatasetType(request.getDatasetType());
        distribution.setPathToLocalDataset(pathToDataset);
        distribution.setPathToRemoteDataset(request.getPathToDataset());

        distribution = distributionRepository.save(distribution);

        log.info("По задаче {} в БД добавлена информация для дистрибьютора. Зарезервированный путь к датасетам: {}",
                task.getId(), pathToDataset);

        return distribution;
    }

    @Override
    @Transactional(readOnly = true)
    public Distribution findByTaskId(Integer taskId) {
        return distributionRepository.findByTask_Id(taskId).
                orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Distribution findByTaskProcessId(UUID processId) {
        return distributionRepository.findByTask_ProcessId(processId)
                .orElse(null);
    }
}

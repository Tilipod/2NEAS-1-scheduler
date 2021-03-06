package ru.tilipod.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.rl4j.learning.sync.qlearning.QLearning;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.controller.dto.TaskStatusChangeDto;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.controller.dto.TrainingResponseDto;
import ru.tilipod.controller.dto.TrainingStatisticDto;
import ru.tilipod.controller.dto.distributor.CloudImagesDownloadRequest;
import ru.tilipod.controller.dto.parser.NeuronNetworkDto;
import ru.tilipod.controller.dto.teacher.TrainingDto;
import ru.tilipod.event.TaskStatusChangeEvent;
import ru.tilipod.exception.EntityNotFoundException;
import ru.tilipod.exception.InvalidDataException;
import ru.tilipod.exception.SystemError;
import ru.tilipod.feign.api.DistributorApi;
import ru.tilipod.feign.api.ParserApi;
import ru.tilipod.feign.api.TeacherApi;
import ru.tilipod.jpa.entity.nneas.Course;
import ru.tilipod.jpa.entity.nneas.Distribution;
import ru.tilipod.jpa.entity.nneas.NeuronNetwork;
import ru.tilipod.jpa.entity.nneas.Precision;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;
import ru.tilipod.jpa.entity.security.User;
import ru.tilipod.jpa.repository.nneas.TaskRepository;
import ru.tilipod.service.CourceService;
import ru.tilipod.service.DistributionService;
import ru.tilipod.service.NeuronNetworkService;
import ru.tilipod.service.PrecisionService;
import ru.tilipod.service.TaskService;
import ru.tilipod.service.UserService;
import ru.tilipod.util.Constants;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final int DEFAULT_COUNT_EPOCH_FOR_DL_TRAINING = 1;
    private static final int DEFAULT_COUNT_EPOCH_FOR_RL_TRAINING = 100;

    private final TaskRepository taskRepository;

    private final ObjectMapper objectMapper;

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    private final DistributionService distributionService;

    private final NeuronNetworkService neuronNetworkService;

    private final CourceService courceService;

    private final ParserApi parserApi;

    private final DistributorApi distributorApi;

    private final TeacherApi teacherApi;

    private final PrecisionService precisionService;

    @Override
    @Transactional(readOnly = true)
    public TrainingResponseDto getTaskStatusByProcessId(UUID processId) {
        TrainingResponseDto response = new TrainingResponseDto();
        Task task = taskRepository.findByProcessId(processId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("???????????????? ?? ID = %s ???? ??????????????.",processId), Task.class));

        response.setStatus(task.getStatus());
        response.setComment(task.getComment());

        Precision precision = precisionService.findLastByTaskId(task.getId());
        if (precision != null) {
            response.setCurrentPrecision(precision.getPrecision());
        }

        return response;
    }

    @Override
    @Transactional
    public byte[] getTaskTrainingResult(UUID processId) {
        Task task = findByProcessId(processId);
        NeuronNetwork net = neuronNetworkService.findByTaskProcessId(processId);

        if (TaskStatusEnum.TRAINED.equals(task.getStatus())) {
            changeStatus(task, TaskStatusEnum.CONFIRMED, "?????????????????? ????????????????");
        }

        try (InputStream in = new FileInputStream(net.getPathToModel())) {
            return in.readAllBytes();
        } catch (Exception e) {
            log.error("???????????? ???????????? ?????????????????? ???????????? ???? ???????????? {}. ????????????: {}", task.getId(), e.getMessage());
            throw new SystemError("???????????? ???????????? ?????????????????? ????????????. ????????????????????, ???????????????????? ?? ????????????????????????");
        }
    }

    @Override
    @Transactional
    public UUID createNewTask(TrainingRequestDto request) {
        User user = userService.findByUuid(request.getUserUuid())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with uuid = %s not found", request.getUserUuid()), User.class));
        Task task = new Task();

        try {
            task.setJsonNetworkStructure(objectMapper.writeValueAsString(request.getNeuronNetworkStructure()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new InvalidDataException("???????????? ?? ???????????? ??????????????.", request);
        }
        task.setStatus(TaskStatusEnum.CREATED);
        task.setProcessId(UUID.randomUUID());
        task.setUser(user);

        task = taskRepository.save(task);

        log.info("Created new task with process_id = {} for user with uuid = {}", task.getProcessId(), user.getUuid());

        distributionService.createNewFromClientRequest(request, task);
        NeuronNetwork net = neuronNetworkService.createNetworkFromClientRequest(request, task);
        courceService.createCourseFromClientRequest(request, net);

        return task.getProcessId();
    }

    @Override
    @Transactional
    public Task changeStatus(Task task, TaskStatusEnum newStatus, String comment) {
        log.info("?????????????????? ?????????????? ???????????? {} ?? {} ???? {}", task.getId(), task.getStatus(), newStatus);

        task.setStatus(newStatus);
        task.setComment(comment);
        task.setLastUpdatedDateTime(ZonedDateTime.now(Constants.EUROPE_MOSCOW_ZONE));
        task = taskRepository.save(task);

        eventPublisher.publishEvent(new TaskStatusChangeEvent(this, task, newStatus));

        return task;
    }

    @Override
    @Transactional
    public void changeStatus(UUID taskId, TaskStatusChangeDto statusChangeDto) {
        Task task = findByProcessId(taskId);
        if (task == null) {
            throw new EntityNotFoundException(String.format("???????????? ?? processId = %s ???? ??????????????.", taskId), Task.class);
        }

        task.setStatus(statusChangeDto.getNewStatus());
        task.setComment(statusChangeDto.getComment());

        log.info("???????????????????????? ?? uuid = {} ?????????????? ???????????? ???????????? {} ???? {}",
                statusChangeDto.getUserUuid(), task.getId(), task.getStatus());

        taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Task findByProcessId(UUID processId) {
        return taskRepository.findByProcessId(processId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("???????????? ?? processId = %s ???? ??????????????.", processId),
                        Task.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Task findById(Integer taskId) {
        return taskRepository.findById(taskId)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAllByStatus(TaskStatusEnum status) {
        return taskRepository.findAllByStatus(status);
    }

    @Override
    @Transactional
    public int prepareAndSendToParser(Task task) {
        NeuronNetwork net = neuronNetworkService.findByTaskId(task.getId());
        NeuronNetworkDto request;

        try {
            request = objectMapper.readValue(task.getJsonNetworkStructure(), NeuronNetworkDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("???????????? ???????????????????????????? json-?????????????????? ?????????????????? ???? ???????????? {}", task.getId());
            changeStatus(task, TaskStatusEnum.SYSTEM_ERROR, "???????????? ???????????????????????????? json-?????????????????? ??????????????????");
            return 0;
        }

        if (net.getWithMentoring()) {
            request.getReforcement().setCountEpoch(DEFAULT_COUNT_EPOCH_FOR_RL_TRAINING);
        }

        request.setTaskId(task.getId());
        request.setPathToSave(net.getPathToModel());

        parserApi.parseNeuronNetworkUsingPOST(request, net.getWithMentoring());

        changeStatus(task, TaskStatusEnum.ANALYSIS, "???????????????????? ???? ????????????");

        return 1;
    }

    @Override
    @Transactional
    public int prepareAndSendToDistributor(Task task) {
        Distribution distribution = distributionService.findByTaskId(task.getId());
        CloudImagesDownloadRequest request = new CloudImagesDownloadRequest();

        request.setTaskId(task.getId());
        request.setCloudType(distribution.getCloudType());
        request.setPathFrom(distribution.getPathToRemoteDataset());
        request.setPathTo(distribution.getPathToLocalDataset());
        request.setToken(distribution.getCloudToken());
        request.setMaxCounts(task.getUser().getVipLevel().getDownloadBatchSize());
        request.setTotalOffset(distribution.getTotal());

        distributorApi.downloadImagesFromCloudUsingPOST(request);

        changeStatus(task, TaskStatusEnum.DISTRIBUTING, "???????????????????? ???? ???????????????? ??????????????????");

        return 1;
    }

    @Override
    @Transactional
    public int prepareAndSendToTeacher(Task task) {
        TrainingDto request = new TrainingDto();
        NeuronNetwork net = neuronNetworkService.findByTaskId(task.getId());
        Course course = courceService.findByTaskId(task.getId());
        Distribution distribution = distributionService.findByTaskId(task.getId());

        request.setTaskId(task.getId());
        request.setCountOutput(net.getCountOutputs());
        request.setDatasetType(distribution.getDatasetType());
        request.setPathToDataset(distribution.getPathToLocalDataset());
        request.setPathToModel(net.getPathToModel());

        if (net.getWithMentoring()) {
            request.setCountStates(course.getCountStates());
            request.setCountEpoch(DEFAULT_COUNT_EPOCH_FOR_RL_TRAINING);
            try {
                request.setReforcementConf(objectMapper.readValue(net.getJsonRlConfStructure(), QLearning.QLConfiguration.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.error("???????????? ???????????????????????????? json-?????????????????? ????-???????????????????????? ???? ???????????? {}", task.getId());
                changeStatus(task, TaskStatusEnum.SYSTEM_ERROR, "???????????? ???????????????????????????? json-?????????????????? ????-????????????????????????");
                return 0;
            }
        } else {
            request.setCountEpoch(DEFAULT_COUNT_EPOCH_FOR_DL_TRAINING);
        }

        teacherApi.stepTrainingUsingPOST(request, net.getWithMentoring());

        changeStatus(task, TaskStatusEnum.TRAINING, "???????????????????? ???? ????????????????");

        return 1;
    }

    @Override
    @Transactional
    public boolean stopTask(UUID processId) {
        Task task = findByProcessId(processId);

        try {
            changeStatus(task, TaskStatusEnum.STOPPED, "?????????????????????? ????????????????");
        } catch (Exception e) {
            log.error("?????????????????? ?????????????????? ???????????? {}. ??????????????: {}", task.getId(), e.getMessage());
            return false;
        }

        log.info("???????????? {} ?????????????????????? ??????????????????????????", task.getId());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public TrainingStatisticDto getStatistic(UUID taskId) {
        Task task = findByProcessId(taskId);
        if (task == null) {
            throw new EntityNotFoundException(String.format("???????????? ?? processId = %s ???? ??????????????.", taskId), Task.class);
        }
        TrainingStatisticDto statistic = new TrainingStatisticDto();

        statistic.setPrecisions(precisionService.findAllByTaskId(task.getId())
                .stream()
                .map(Precision::getPrecision)
                .collect(Collectors.toList()));

        return statistic;
    }

}

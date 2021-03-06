package ru.tilipod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tilipod.controller.dto.TaskStatusChangeDto;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.controller.dto.TrainingResponseDto;
import ru.tilipod.controller.dto.TrainingStatisticDto;
import ru.tilipod.service.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Api(description = "Контроллер для запросов клиента (API-сервера)")
public class ClientRequestController {

    private final TaskService taskService;

    @PostMapping("/")
    @ApiOperation(value = "Создать новую задачу по обучению нейронной сети")
    public UUID createTask(@RequestBody TrainingRequestDto request) {
        return taskService.createNewTask(request);
    }

    @GetMapping("/{taskId}")
    @ApiOperation(value = "Получить статус задачи по обучению нейронной сети")
    public TrainingResponseDto getTaskStatus(@PathVariable UUID taskId) {
        return taskService.getTaskStatusByProcessId(taskId);
    }

    @GetMapping("/{taskId}/result")
    @ApiOperation(value = "Получить результат задачи по обучению нейронной сети")
    public ResponseEntity<byte[]> getTaskTrainingResult(@PathVariable UUID taskId) {
        return ResponseEntity.ok(taskService.getTaskTrainingResult(taskId));
    }

    @PostMapping("/{taskId}/stop")
    @ApiOperation(value = "Остановить задачу по обучению нейронной сети")
    public Boolean stopTraining(@PathVariable UUID taskId) {
        return taskService.stopTask(taskId);
    }

    @PutMapping("/{taskId}/status")
    @ApiOperation(value = "Обновить статус задачи по обучению нейронной сети")
    public ResponseEntity<Void> changeTaskStatus(@PathVariable UUID taskId, @RequestBody TaskStatusChangeDto statusChangeDto) {
        taskService.changeStatus(taskId, statusChangeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{taskId}/statistic")
    @ApiOperation(value = "Получить статистику обучения нейронной сети")
    public TrainingStatisticDto getStatistic(@PathVariable UUID taskId) {
        return taskService.getStatistic(taskId);
    }

}

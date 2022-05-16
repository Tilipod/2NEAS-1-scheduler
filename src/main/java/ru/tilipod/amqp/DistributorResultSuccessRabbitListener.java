package ru.tilipod.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.DistributeResultSuccessMessage;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;
import ru.tilipod.service.TaskService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DistributorResultSuccessRabbitListener {

    private final TaskService taskService;

    @RabbitListener(queues = "${queues.distributorResultSuccess}")
    @Transactional
    public void worker(DistributeResultSuccessMessage message) {
        log.info("Получено сообщение об успешной выгрузке датасетов задачи {}", message.getTaskId());

        Task task = taskService.findById(message.getTaskId());
        if (task == null) {
            log.warn("Задача с id = {} не найдена в БД. Игнорируем сообщение об успешной выгрузке датасетов", message.getTaskId());
            return;
        }

        if (!TaskStatusEnum.DISTRIBUTING.equals(task.getStatus())) {
            log.warn("Задача с id = {} находится в статусе {}. Игнорируем сообщение об успешной выгрузке датасетов", task.getId(), task.getStatus());
            return;
        }

        taskService.changeStatus(task, TaskStatusEnum.DISTRIBUTED, "Ожидает обучения");
    }

}

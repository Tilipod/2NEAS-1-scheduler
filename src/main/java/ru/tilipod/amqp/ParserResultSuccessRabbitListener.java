package ru.tilipod.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.ParserResultSuccessMessage;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;
import ru.tilipod.service.TaskService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParserResultSuccessRabbitListener {

    private final TaskService taskService;

    @RabbitListener(queues = "${queues.parserResultSuccess}")
    @Transactional
    public void worker(ParserResultSuccessMessage message) {
        log.info("Получено сообщение об успешном анализе задачи {}", message.getTaskId());

        Task task = taskService.findById(message.getTaskId());
        if (task == null) {
            log.warn("Задача с id = {} не найдена в БД. Игнорируем сообщение об успешном анализе", message.getTaskId());
            return;
        }

        if (!TaskStatusEnum.ANALYSIS.equals(task.getStatus())) {
            log.warn("Задача с id = {} находится в статусе {}. Игнорируем сообщение об успешном анализе", task.getId(), task.getStatus());
            return;
        }

        taskService.changeStatus(task, TaskStatusEnum.ANALYZED, "Ожидает выгрузки датасетов");
    }

}

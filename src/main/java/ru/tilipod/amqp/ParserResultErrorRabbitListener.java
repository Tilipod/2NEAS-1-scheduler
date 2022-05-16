package ru.tilipod.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.ParserResultErrorMessage;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;
import ru.tilipod.service.TaskService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParserResultErrorRabbitListener {

    private final TaskService taskService;

    @RabbitListener(queues = "${queues.parserResultError}")
    @Transactional
    public void worker(ParserResultErrorMessage message) {
        log.info("Получено сообщение о неудачном анализе задачи {}. Класс ошибки: {}", message.getTaskId(), message.getClassName());

        Task task = taskService.findById(message.getTaskId());
        if (task == null) {
            log.warn("Задача с id = {} не найдена в БД. Игнорируем сообщение о неудачном анализе", message.getTaskId());
            return;
        }

        if (!TaskStatusEnum.ANALYSIS.equals(task.getStatus())) {
            log.warn("Задача с id = {} находится в статусе {}. Игнорируем сообщение о неудачном анализе", task.getId(), task.getStatus());
            return;
        }

        taskService.changeStatus(task, TaskStatusEnum.ANALYSIS_ERROR, message.getMessage());
    }
}

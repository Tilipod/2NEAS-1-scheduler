package ru.tilipod.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.TeacherResultErrorMessage;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;
import ru.tilipod.service.TaskService;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeacherResultErrorRabbitListener {

    private final TaskService taskService;

    @RabbitListener(queues = "${queues.teacherResultError}")
    @Transactional
    public void worker(TeacherResultErrorMessage message) {
        log.info("Получено сообщение о неудачном обучении задачи {}. Класс ошибки: {}", message.getTaskId(), message.getClassName());

        Task task = taskService.findById(message.getTaskId());
        if (task == null) {
            log.warn("Задача с id = {} не найдена в БД. Игнорируем сообщение о неудачном обучении", message.getTaskId());
            return;
        }

        if (!TaskStatusEnum.TRAINING.equals(task.getStatus())) {
            log.warn("Задача с id = {} находится в статусе {}. Игнорируем сообщение о неудачном обучении", task.getId(), task.getStatus());
            return;
        }

        taskService.changeStatus(task, TaskStatusEnum.TRAINING_ERROR, message.getMessage());
    }
}

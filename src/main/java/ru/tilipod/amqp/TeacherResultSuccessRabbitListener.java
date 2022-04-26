package ru.tilipod.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.TeacherResultSuccessMessage;
import ru.tilipod.jpa.entity.Course;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;
import ru.tilipod.service.CourceService;
import ru.tilipod.service.TaskService;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeacherResultSuccessRabbitListener {

    private final CourceService courceService;

    private final TaskService taskService;

    @RabbitListener(queues = "${queues.teacherResultSuccess}")
    @Transactional
    public void worker(TeacherResultSuccessMessage message) {
        log.info("Получено сообщение об успешном обучении задачи {}", message.getTaskId());

        Task task = taskService.findById(message.getTaskId());
        if (task == null) {
            log.warn("Задача с id = {} не найдена в БД. Игнорируем сообщение об успешном обучении", message.getTaskId());
            return;
        }

        Course course = courceService.findByTaskId(task.getId());
        if (course == null) {
            log.error("Course для задачи {} не найден в БД. Критическая ошибка", task.getId());
            taskService.changeStatus(task, TaskStatusEnum.SYSTEM_ERROR, "Не найден Course по задаче. Пожалуйста, обратитесь к разработчику");
            return;
        }

        if (!TaskStatusEnum.TRAINING.equals(task.getStatus())) {
            log.warn("Задача с id = {} находится в статусе {}. Игнорируем сообщение об успешном обучении", task.getId(), task.getStatus());
            return;
        }

        course.setCurrentEpoch(course.getCurrentEpoch() + 1);
        if (course.getCurrentEpoch() >= course.getCountEpoch()) {
            taskService.changeStatus(task, TaskStatusEnum.TRAINED, "Полностью обучена");
        } else {
            taskService.changeStatus(task, TaskStatusEnum.DISTRIBUTED, String.format("Обучено %d эпох из %d",
                    course.getCurrentEpoch(), course.getCountEpoch()));
        }
    }

}

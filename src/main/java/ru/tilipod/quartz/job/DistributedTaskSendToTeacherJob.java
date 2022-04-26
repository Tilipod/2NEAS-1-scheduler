package ru.tilipod.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tilipod.jpa.entity.Task;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;
import ru.tilipod.service.TaskService;

import java.util.List;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DistributedTaskSendToTeacherJob implements Job {

    @Autowired
    private TaskService taskService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Task> tasks = taskService.findAllByStatus(TaskStatusEnum.DISTRIBUTED);

        int count = tasks.stream()
                .map(taskService::prepareAndSendToTeacher)
                .reduce(0, Integer::sum);

        log.info("Отправлено {} задач на обучение", count);
    }
}

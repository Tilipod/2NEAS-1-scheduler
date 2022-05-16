package ru.tilipod.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;
import ru.tilipod.service.TaskService;
import ru.tilipod.util.Constants;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DistributingTaskAgainSendToDistributorJob implements Job {

    @Autowired
    private TaskService taskService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       List<Task> tasks = taskService.findAllByStatus(TaskStatusEnum.DISTRIBUTING);

       // Каждые 2 часа повторно запускаем задачи на выгрузку датасетов. Вдруг упали
       int count = tasks.stream()
               .filter(t -> ChronoUnit.HOURS.between(t.getLastUpdatedDateTime().withZoneSameInstant(Constants.EUROPE_MOSCOW_ZONE),
                       ZonedDateTime.now(Constants.EUROPE_MOSCOW_ZONE)) >= 2)
               .map(taskService::prepareAndSendToDistributor)
               .reduce(0, Integer::sum);

       log.info("Отправлено {} задач на повторную выгрузку датасетов", count);
    }

}

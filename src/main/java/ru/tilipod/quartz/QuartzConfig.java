package ru.tilipod.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

@Configuration
@Order(1000)
@ConditionalOnProperty(name = "quartz.enabled")
@EnableConfigurationProperties(SchedulerProperties.class)
@Slf4j
public class QuartzConfig {

    private final ApplicationContext applicationContext;
    private final DataSource dataSource;
    private final SchedulerProperties schedulerProperties;

    @Autowired
    public QuartzConfig(ApplicationContext applicationContext,
                        DataSource dataSource,
                        SchedulerProperties schedulerProperties) {
        this.applicationContext = applicationContext;
        this.dataSource = dataSource;
        this.schedulerProperties = schedulerProperties;
    }

    @Bean
    public SpringBeanJobFactory jobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(List<Trigger> triggers)
            throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(quartzProperties());

        factory.setWaitForJobsToCompleteOnShutdown(true);
        factory.afterPropertiesSet();

        Scheduler scheduler = factory.getScheduler();
        scheduler.setJobFactory(jobFactory());
        scheduler.clear();

        for (Trigger trigger : triggers) {
            JobDetail jobDetail = (JobDetail) trigger.getJobDataMap().get("jobDetail");
            if (!scheduler.checkExists(jobDetail.getKey())) {
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                scheduler.scheduleJob(trigger);
            }
        }

        scheduler.start();
        return scheduler;
    }

    @Bean
    public List<Trigger> triggers() {
        List<Trigger> triggers = new ArrayList<>();

        for (JobDetailProperties jobDetailProperties : schedulerProperties.getJobDetails()) {
            if (jobDetailProperties.getJobClass() != null) {
                JobDetail jobDetail = createJobDetail(jobDetailProperties);

                if (jobDetailProperties.getTriggers() != null) {
                    for (TriggerProperties triggerProperties : jobDetailProperties.getTriggers()) {
                        Trigger trigger = switch (triggerProperties.getTriggerType()) {
                            case CRON -> createCronTrigger(jobDetail, triggerProperties);
                            case SIMPLE -> createTrigger(jobDetail, triggerProperties);
                        };

                        if (trigger != null) {
                            triggers.add(trigger);
                        }
                    }
                }
            }
        }

        return triggers;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    private static JobDetail createJobDetail(JobDetailProperties properties) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(properties.getJobClass());

        factoryBean.setDurability(true);
        factoryBean.setDescription(properties.getDescription());

        if (StringUtils.hasText(properties.getName())) {
            factoryBean.setName(properties.getName());
        }

        if (StringUtils.hasText(properties.getGroup())) {
            factoryBean.setGroup(properties.getGroup());
        }

        if (properties.getJobDataMap() != null) {
            Map<String, Object> jobDataMap = new HashMap<>();

            for (String key : properties.getJobDataMap().getKeys()) {
                Map<String, Object> map = (Map<String, Object>) properties.getJobDataMap().get(key);
                jobDataMap.putAll(map);
            }

            factoryBean.setJobDataAsMap(jobDataMap);
        }

        factoryBean.afterPropertiesSet();


        return factoryBean.getObject();
    }

    private static Trigger createTrigger(JobDetail jobDetail, TriggerProperties properties) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setName(properties.getName());
        factoryBean.setGroup(properties.getGroup());
        factoryBean.setRepeatInterval(properties.getRepeatInterval());
        factoryBean.setDescription(properties.getDescription());
        factoryBean.setPriority(properties.getPriority());
        factoryBean.setStartDelay(properties.getStartDelay());
        factoryBean.setRepeatCount(properties.getRepeatCount());
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    private static Trigger createCronTrigger(JobDetail jobDetail, TriggerProperties properties) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Moscow")));
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setName(properties.getName());
        factoryBean.setGroup(properties.getGroup());
        factoryBean.setCronExpression(properties.getCronExpression());
        factoryBean.setDescription(properties.getDescription());
        factoryBean.setPriority(properties.getPriority());
        factoryBean.setStartDelay(properties.getStartDelay());
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            log.error("Factory bean afterPropertiesSet error", e);
        }

        return factoryBean.getObject();
    }

}
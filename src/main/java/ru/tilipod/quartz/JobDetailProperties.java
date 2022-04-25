package ru.tilipod.quartz;

import lombok.Data;
import org.quartz.Job;
import org.quartz.JobDataMap;

import java.util.List;

@Data
public class JobDetailProperties {
    private String name;

    private String group;

    private String description;

    private JobDataMap jobDataMap;

    private Class<? extends Job> jobClass;

    private List<TriggerProperties> triggers;
}

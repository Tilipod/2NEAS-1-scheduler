package ru.tilipod.quartz;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "scheduler")
@Data
public class SchedulerProperties {
    private List<JobDetailProperties> jobDetails;
}

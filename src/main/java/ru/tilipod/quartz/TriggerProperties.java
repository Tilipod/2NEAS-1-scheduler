package ru.tilipod.quartz;

import lombok.Data;

@Data
public class TriggerProperties {
    private TriggerType triggerType;

    private String name;

    private String group;

    private String description;

    private int priority;

    private long startDelay;

    private int repeatCount;

    private long repeatInterval;

    private String cronExpression;

    private String startTime;
}

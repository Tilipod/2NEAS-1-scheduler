package ru.tilipod.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;

@Getter
public class TaskStatusChangeEvent extends ApplicationEvent {

    private final Task task;

    private final TaskStatusEnum newStatus;

    public TaskStatusChangeEvent(Object object, Task task, TaskStatusEnum newStatus) {
        super(object);
        this.task = task;
        this.newStatus = newStatus;
    }
}

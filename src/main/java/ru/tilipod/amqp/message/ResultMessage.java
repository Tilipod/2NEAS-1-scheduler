package ru.tilipod.amqp.message;

import lombok.Data;

@Data
public abstract class ResultMessage {

    private Integer taskId;
}

package ru.tilipod.amqp.message;

import lombok.Data;

@Data
public class DistributeResultSuccessMessage extends ResultMessage {

    private String pathTo;

    public static DistributeResultSuccessMessage createMessage(Integer taskId, String pathTo) {
        DistributeResultSuccessMessage model = new DistributeResultSuccessMessage();

        model.setTaskId(taskId);
        model.setPathTo(pathTo);

        return model;
    }
}

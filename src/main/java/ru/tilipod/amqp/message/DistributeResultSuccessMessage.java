package ru.tilipod.amqp.message;

import lombok.Data;

@Data
public class DistributeResultSuccessMessage extends ResultMessage {

    private String pathTo;

    private Long downloadCount;

    public static DistributeResultSuccessMessage createMessage(Integer taskId, String pathTo, Long downloadCount) {
        DistributeResultSuccessMessage model = new DistributeResultSuccessMessage();

        model.setTaskId(taskId);
        model.setPathTo(pathTo);
        model.setDownloadCount(downloadCount);

        return model;
    }
}

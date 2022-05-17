package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;

import java.util.UUID;

@Data
@ApiModel(value = "TaskStatusChange", description = "Данные для обновления статуса задачи")
public class TaskStatusChangeDto {

    @ApiModelProperty(value = "Новый статус задачи", required = true)
    private TaskStatusEnum newStatus;

    @ApiModelProperty(value = "Комментарий к изменению статуса", required = true)
    private String comment;

    @ApiModelProperty(value = "ID пользователя, изменившего статус", required = true)
    private UUID userUuid;
}

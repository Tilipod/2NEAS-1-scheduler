package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;

@Data
@ApiModel(value = "TrainingResponse", description = "Обученная нейронная сеть")
public class TrainingResponseDto {

    @ApiModelProperty(value = "Текущий статус задачи на обучение")
    private TaskStatusEnum status;

    @ApiModelProperty(value = "Комментарий к статусу")
    private String comment;
}

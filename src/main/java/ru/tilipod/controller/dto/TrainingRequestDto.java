package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.tilipod.jpa.entity.enums.NeuronNetworkType;

import java.util.List;

@Data
@ApiModel(value = "TrainingRequest", description = "Запрос на создание задачи по обучению нейронной сети")
public class TrainingRequestDto {

    @ApiModelProperty("Список слоев нейронной сети")
    private List<LayerDto> layers;

    @ApiModelProperty("Тип нейронной сети")
    private NeuronNetworkType neuronNetworkType;

    @ApiModelProperty("Обучение с подкреплением")
    private Boolean withMentoring;
}

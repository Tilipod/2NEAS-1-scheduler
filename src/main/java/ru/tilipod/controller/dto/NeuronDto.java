package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "Neuron", description = "Нейрон")
public class NeuronDto {

    @ApiModelProperty("Порядковый номер нейрона")
    private Integer orderNumber;

    @ApiModelProperty("Список порядковых номеров нейронов, к которым есть связь от текущего")
    private List<Integer> relationsWith;
}

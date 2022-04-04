package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.tilipod.jpa.entity.enums.LayerType;

import java.util.List;

@Data
@ApiModel(value = "Layer", description = "Слой нейронной сети")
public class LayerDto {

    @ApiModelProperty("Порядковый номер слоя")
    private Integer orderNumber;

    @ApiModelProperty("Список нейронов слоя")
    private List<NeuronDto> neurons;

    @ApiModelProperty("Тип слоя")
    private LayerType type;
}

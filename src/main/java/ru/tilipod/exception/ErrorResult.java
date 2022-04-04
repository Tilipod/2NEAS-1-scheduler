package ru.tilipod.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(description = "Ошибка запроса")
@Builder
public class ErrorResult {

    @ApiModelProperty("Сообщение об ошибке")
    private String message;

    @ApiModelProperty("Статус ошибки")
    private Integer status;

}

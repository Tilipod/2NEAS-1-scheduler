package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.tilipod.controller.dto.distributor.CloudImagesDownloadRequest;
import ru.tilipod.controller.dto.parser.NeuronNetworkDto;
import ru.tilipod.controller.dto.teacher.TrainingDto;

import java.util.UUID;

@Data
@ApiModel(value = "TrainingRequest", description = "Запрос на создание задачи по обучению нейронной сети")
public class TrainingRequestDto {

    @ApiModelProperty(value = "UUID пользователя")
    private UUID userUuid;

    @ApiModelProperty(value = "Включить подкрепление к обучению")
    private Boolean withMentoring;

    @ApiModelProperty(value = "Кол-во эпох для обучения нейросети")
    private Integer countEpoch;

    @ApiModelProperty(value = "Тип данных для обучения нейросети")
    private TrainingDto.DatasetTypeEnum datasetType;

    @ApiModelProperty(value = "Тип облака для выгрузки датасетов")
    private CloudImagesDownloadRequest.CloudTypeEnum cloudType;

    @ApiModelProperty(value = "Путь к папке в облаке с датасетами")
    private String pathToDataset;

    @ApiModelProperty(value = "Токен для авторизации в облаке")
    private String cloudToken;

    @ApiModelProperty(value = "Структура нейросети")
    private NeuronNetworkDto neuronNetworkStructure;
}

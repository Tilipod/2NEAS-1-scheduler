package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "TrainingStatistic", description = "Статистика обучения нейросети")
public class TrainingStatisticDto {

    private List<Double> precisions;
}

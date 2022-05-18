package ru.tilipod.controller.dto.teacher;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.deeplearning4j.rl4j.learning.sync.qlearning.QLearning;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TrainingDto
 */
public class TrainingDto   {
  @JsonProperty("countEpoch")
  private Integer countEpoch;

  @JsonProperty("countOutput")
  private Integer countOutput;

  @JsonProperty("countStates")
  private Integer countStates;

  /**
   * Тип данных для обучения сети
   */
  public enum DatasetTypeEnum {
    IMAGE("IMAGE");

    private String value;

    DatasetTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DatasetTypeEnum fromValue(String value) {
      for (DatasetTypeEnum b : DatasetTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("datasetType")
  private DatasetTypeEnum datasetType;

  @JsonProperty("pathToDataset")
  private String pathToDataset;

  @JsonProperty("pathToModel")
  private String pathToModel;

  @JsonProperty("reforcementConf")
  private QLearning.QLConfiguration reforcementConf;

  @JsonProperty("taskId")
  private Integer taskId;

  public TrainingDto countEpoch(Integer countEpoch) {
    this.countEpoch = countEpoch;
    return this;
  }

  /**
   * Кол-во эпох для обучения
   * @return countEpoch
  */
  @ApiModelProperty(required = true, value = "Кол-во эпох для обучения")
  @NotNull


  public Integer getCountEpoch() {
    return countEpoch;
  }

  public void setCountEpoch(Integer countEpoch) {
    this.countEpoch = countEpoch;
  }

  public TrainingDto countOutput(Integer countOutput) {
    this.countOutput = countOutput;
    return this;
  }

  /**
   * Кол-во выходов нейронной сети
   * @return countOutput
  */
  @ApiModelProperty(required = true, value = "Кол-во выходов нейронной сети")
  @NotNull


  public Integer getCountOutput() {
    return countOutput;
  }

  public void setCountOutput(Integer countOutput) {
    this.countOutput = countOutput;
  }

  public TrainingDto countStates(Integer countStates) {
    this.countStates = countStates;
    return this;
  }

  /**
   * Количество состояний для обучения с подкреплением
   * @return countStates
  */
  @ApiModelProperty(value = "Количество состояний для обучения с подкреплением")


  public Integer getCountStates() {
    return countStates;
  }

  public void setCountStates(Integer countStates) {
    this.countStates = countStates;
  }

  public TrainingDto datasetType(DatasetTypeEnum datasetType) {
    this.datasetType = datasetType;
    return this;
  }

  /**
   * Тип данных для обучения сети
   * @return datasetType
  */
  @ApiModelProperty(required = true, value = "Тип данных для обучения сети")
  @NotNull


  public DatasetTypeEnum getDatasetType() {
    return datasetType;
  }

  public void setDatasetType(DatasetTypeEnum datasetType) {
    this.datasetType = datasetType;
  }

  public TrainingDto pathToDataset(String pathToDataset) {
    this.pathToDataset = pathToDataset;
    return this;
  }

  /**
   * Путь к датасетам для обучения
   * @return pathToDataset
  */
  @ApiModelProperty(required = true, value = "Путь к датасетам для обучения")
  @NotNull


  public String getPathToDataset() {
    return pathToDataset;
  }

  public void setPathToDataset(String pathToDataset) {
    this.pathToDataset = pathToDataset;
  }

  public TrainingDto pathToModel(String pathToModel) {
    this.pathToModel = pathToModel;
    return this;
  }

  /**
   * Путь к модели сети
   * @return pathToModel
  */
  @ApiModelProperty(required = true, value = "Путь к модели сети")
  @NotNull


  public String getPathToModel() {
    return pathToModel;
  }

  public void setPathToModel(String pathToModel) {
    this.pathToModel = pathToModel;
  }

  public TrainingDto reforcementConf(QLearning.QLConfiguration reforcementConf) {
    this.reforcementConf = reforcementConf;
    return this;
  }

  /**
   * Get reforcementConf
   * @return reforcementConf
  */
  @ApiModelProperty(value = "")

  @Valid

  public QLearning.QLConfiguration getReforcementConf() {
    return reforcementConf;
  }

  public void setReforcementConf(QLearning.QLConfiguration reforcementConf) {
    this.reforcementConf = reforcementConf;
  }

  public TrainingDto taskId(Integer taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * ID задачи
   * @return taskId
  */
  @ApiModelProperty(required = true, value = "ID задачи")
  @NotNull


  public Integer getTaskId() {
    return taskId;
  }

  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrainingDto trainingDto = (TrainingDto) o;
    return Objects.equals(this.countEpoch, trainingDto.countEpoch) &&
        Objects.equals(this.countOutput, trainingDto.countOutput) &&
        Objects.equals(this.countStates, trainingDto.countStates) &&
        Objects.equals(this.datasetType, trainingDto.datasetType) &&
        Objects.equals(this.pathToDataset, trainingDto.pathToDataset) &&
        Objects.equals(this.pathToModel, trainingDto.pathToModel) &&
        Objects.equals(this.reforcementConf, trainingDto.reforcementConf) &&
        Objects.equals(this.taskId, trainingDto.taskId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countEpoch, countOutput, countStates, datasetType, pathToDataset, pathToModel, reforcementConf, taskId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TrainingDto {\n");
    
    sb.append("    countEpoch: ").append(toIndentedString(countEpoch)).append("\n");
    sb.append("    countOutput: ").append(toIndentedString(countOutput)).append("\n");
    sb.append("    countStates: ").append(toIndentedString(countStates)).append("\n");
    sb.append("    datasetType: ").append(toIndentedString(datasetType)).append("\n");
    sb.append("    pathToDataset: ").append(toIndentedString(pathToDataset)).append("\n");
    sb.append("    pathToModel: ").append(toIndentedString(pathToModel)).append("\n");
    sb.append("    reforcementConf: ").append(toIndentedString(reforcementConf)).append("\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


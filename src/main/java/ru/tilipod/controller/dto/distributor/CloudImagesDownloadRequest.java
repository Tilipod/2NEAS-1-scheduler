package ru.tilipod.controller.dto.distributor;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Запрос на выгрузку изображений из облака на диск
 */
@ApiModel(description = "Запрос на выгрузку изображений из облака на диск")
public class CloudImagesDownloadRequest   {
  /**
   * Тип облака
   */
  public enum CloudTypeEnum {
    YANDEX("YANDEX");

    private String value;

    CloudTypeEnum(String value) {
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
    public static CloudTypeEnum fromValue(String value) {
      for (CloudTypeEnum b : CloudTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("cloudType")
  private CloudTypeEnum cloudType;

  @JsonProperty("pathFrom")
  private String pathFrom;

  @JsonProperty("pathTo")
  private String pathTo;

  @JsonProperty("taskId")
  private Integer taskId;

  @JsonProperty("token")
  private String token;

  public CloudImagesDownloadRequest cloudType(CloudTypeEnum cloudType) {
    this.cloudType = cloudType;
    return this;
  }

  /**
   * Тип облака
   * @return cloudType
  */
  @ApiModelProperty(value = "Тип облака")


  public CloudTypeEnum getCloudType() {
    return cloudType;
  }

  public void setCloudType(CloudTypeEnum cloudType) {
    this.cloudType = cloudType;
  }

  public CloudImagesDownloadRequest pathFrom(String pathFrom) {
    this.pathFrom = pathFrom;
    return this;
  }

  /**
   * Путь к корневой папке в облаке
   * @return pathFrom
  */
  @ApiModelProperty(value = "Путь к корневой папке в облаке")


  public String getPathFrom() {
    return pathFrom;
  }

  public void setPathFrom(String pathFrom) {
    this.pathFrom = pathFrom;
  }

  public CloudImagesDownloadRequest pathTo(String pathTo) {
    this.pathTo = pathTo;
    return this;
  }

  /**
   * Путь к корневой папке на диске
   * @return pathTo
  */
  @ApiModelProperty(value = "Путь к корневой папке на диске")


  public String getPathTo() {
    return pathTo;
  }

  public void setPathTo(String pathTo) {
    this.pathTo = pathTo;
  }

  public CloudImagesDownloadRequest taskId(Integer taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * ID задачи
   * @return taskId
  */
  @ApiModelProperty(value = "ID задачи")


  public Integer getTaskId() {
    return taskId;
  }

  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }

  public CloudImagesDownloadRequest token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Токен для авторизации в облаке
   * @return token
  */
  @ApiModelProperty(value = "Токен для авторизации в облаке")


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CloudImagesDownloadRequest cloudImagesDownloadRequest = (CloudImagesDownloadRequest) o;
    return Objects.equals(this.cloudType, cloudImagesDownloadRequest.cloudType) &&
        Objects.equals(this.pathFrom, cloudImagesDownloadRequest.pathFrom) &&
        Objects.equals(this.pathTo, cloudImagesDownloadRequest.pathTo) &&
        Objects.equals(this.taskId, cloudImagesDownloadRequest.taskId) &&
        Objects.equals(this.token, cloudImagesDownloadRequest.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cloudType, pathFrom, pathTo, taskId, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CloudImagesDownloadRequest {\n");
    
    sb.append("    cloudType: ").append(toIndentedString(cloudType)).append("\n");
    sb.append("    pathFrom: ").append(toIndentedString(pathFrom)).append("\n");
    sb.append("    pathTo: ").append(toIndentedString(pathTo)).append("\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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


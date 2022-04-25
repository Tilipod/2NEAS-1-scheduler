package ru.tilipod.controller.dto.parser;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * InputTypeDto
 */
public class InputTypeDto   {
  @JsonProperty("channels")
  private Integer channels;

  @JsonProperty("height")
  private Integer height;

  /**
   * Тип входных данных
   */
  public enum InputTypeEnum {
    CONVOLUTIONAL("CONVOLUTIONAL");

    private String value;

    InputTypeEnum(String value) {
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
    public static InputTypeEnum fromValue(String value) {
      for (InputTypeEnum b : InputTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("inputType")
  private InputTypeEnum inputType;

  @JsonProperty("weight")
  private Integer weight;

  public InputTypeDto channels(Integer channels) {
    this.channels = channels;
    return this;
  }

  /**
   * Кол-во каналов (для СНС)
   * @return channels
  */
  @ApiModelProperty(value = "Кол-во каналов (для СНС)")


  public Integer getChannels() {
    return channels;
  }

  public void setChannels(Integer channels) {
    this.channels = channels;
  }

  public InputTypeDto height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * Высота изображения (для СНС)
   * @return height
  */
  @ApiModelProperty(value = "Высота изображения (для СНС)")


  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public InputTypeDto inputType(InputTypeEnum inputType) {
    this.inputType = inputType;
    return this;
  }

  /**
   * Тип входных данных
   * @return inputType
  */
  @ApiModelProperty(required = true, value = "Тип входных данных")
  @NotNull


  public InputTypeEnum getInputType() {
    return inputType;
  }

  public void setInputType(InputTypeEnum inputType) {
    this.inputType = inputType;
  }

  public InputTypeDto weight(Integer weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Ширина изображения (для СНС)
   * @return weight
  */
  @ApiModelProperty(value = "Ширина изображения (для СНС)")


  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InputTypeDto inputTypeDto = (InputTypeDto) o;
    return Objects.equals(this.channels, inputTypeDto.channels) &&
        Objects.equals(this.height, inputTypeDto.height) &&
        Objects.equals(this.inputType, inputTypeDto.inputType) &&
        Objects.equals(this.weight, inputTypeDto.weight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(channels, height, inputType, weight);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InputTypeDto {\n");
    
    sb.append("    channels: ").append(toIndentedString(channels)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    inputType: ").append(toIndentedString(inputType)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
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


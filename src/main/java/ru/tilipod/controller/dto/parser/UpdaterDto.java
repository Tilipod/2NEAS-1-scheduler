package ru.tilipod.controller.dto.parser;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * UpdaterDto
 */
public class UpdaterDto   {
  @JsonProperty("updaterFactor")
  private Double updaterFactor;

  /**
   * Тип оптимизации скорости обучения
   */
  public enum UpdaterTypeEnum {
    NESTEROVS("NESTEROVS");

    private String value;

    UpdaterTypeEnum(String value) {
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
    public static UpdaterTypeEnum fromValue(String value) {
      for (UpdaterTypeEnum b : UpdaterTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("updaterType")
  private UpdaterTypeEnum updaterType;

  public UpdaterDto updaterFactor(Double updaterFactor) {
    this.updaterFactor = updaterFactor;
    return this;
  }

  /**
   * Коэффициент оптимизации скорости обучения
   * @return updaterFactor
  */
  @ApiModelProperty(value = "Коэффициент оптимизации скорости обучения")


  public Double getUpdaterFactor() {
    return updaterFactor;
  }

  public void setUpdaterFactor(Double updaterFactor) {
    this.updaterFactor = updaterFactor;
  }

  public UpdaterDto updaterType(UpdaterTypeEnum updaterType) {
    this.updaterType = updaterType;
    return this;
  }

  /**
   * Тип оптимизации скорости обучения
   * @return updaterType
  */
  @ApiModelProperty(value = "Тип оптимизации скорости обучения")


  public UpdaterTypeEnum getUpdaterType() {
    return updaterType;
  }

  public void setUpdaterType(UpdaterTypeEnum updaterType) {
    this.updaterType = updaterType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdaterDto updaterDto = (UpdaterDto) o;
    return Objects.equals(this.updaterFactor, updaterDto.updaterFactor) &&
        Objects.equals(this.updaterType, updaterDto.updaterType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updaterFactor, updaterType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdaterDto {\n");
    
    sb.append("    updaterFactor: ").append(toIndentedString(updaterFactor)).append("\n");
    sb.append("    updaterType: ").append(toIndentedString(updaterType)).append("\n");
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


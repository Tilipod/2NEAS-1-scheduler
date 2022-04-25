package ru.tilipod.controller.dto.parser;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * RegularizationDto
 */
public class RegularizationDto   {
  @JsonProperty("needRegularization")
  private Boolean needRegularization;

  @JsonProperty("regularizationBias")
  private Double regularizationBias;

  @JsonProperty("regularizationFactor")
  private Double regularizationFactor;

  /**
   * Тип регуляризации
   */
  public enum RegularizationTypeEnum {
    L1("L1"),
    
    L2("L2");

    private String value;

    RegularizationTypeEnum(String value) {
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
    public static RegularizationTypeEnum fromValue(String value) {
      for (RegularizationTypeEnum b : RegularizationTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("regularizationType")
  private RegularizationTypeEnum regularizationType;

  public RegularizationDto needRegularization(Boolean needRegularization) {
    this.needRegularization = needRegularization;
    return this;
  }

  /**
   * Требуется ли проводить регуляризацию
   * @return needRegularization
  */
  @ApiModelProperty(value = "Требуется ли проводить регуляризацию")


  public Boolean getNeedRegularization() {
    return needRegularization;
  }

  public void setNeedRegularization(Boolean needRegularization) {
    this.needRegularization = needRegularization;
  }

  public RegularizationDto regularizationBias(Double regularizationBias) {
    this.regularizationBias = regularizationBias;
    return this;
  }

  /**
   * Смещение при регуляризации
   * @return regularizationBias
  */
  @ApiModelProperty(value = "Смещение при регуляризации")


  public Double getRegularizationBias() {
    return regularizationBias;
  }

  public void setRegularizationBias(Double regularizationBias) {
    this.regularizationBias = regularizationBias;
  }

  public RegularizationDto regularizationFactor(Double regularizationFactor) {
    this.regularizationFactor = regularizationFactor;
    return this;
  }

  /**
   * Коэффициент регуляризации
   * @return regularizationFactor
  */
  @ApiModelProperty(value = "Коэффициент регуляризации")


  public Double getRegularizationFactor() {
    return regularizationFactor;
  }

  public void setRegularizationFactor(Double regularizationFactor) {
    this.regularizationFactor = regularizationFactor;
  }

  public RegularizationDto regularizationType(RegularizationTypeEnum regularizationType) {
    this.regularizationType = regularizationType;
    return this;
  }

  /**
   * Тип регуляризации
   * @return regularizationType
  */
  @ApiModelProperty(value = "Тип регуляризации")


  public RegularizationTypeEnum getRegularizationType() {
    return regularizationType;
  }

  public void setRegularizationType(RegularizationTypeEnum regularizationType) {
    this.regularizationType = regularizationType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegularizationDto regularizationDto = (RegularizationDto) o;
    return Objects.equals(this.needRegularization, regularizationDto.needRegularization) &&
        Objects.equals(this.regularizationBias, regularizationDto.regularizationBias) &&
        Objects.equals(this.regularizationFactor, regularizationDto.regularizationFactor) &&
        Objects.equals(this.regularizationType, regularizationDto.regularizationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(needRegularization, regularizationBias, regularizationFactor, regularizationType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegularizationDto {\n");
    
    sb.append("    needRegularization: ").append(toIndentedString(needRegularization)).append("\n");
    sb.append("    regularizationBias: ").append(toIndentedString(regularizationBias)).append("\n");
    sb.append("    regularizationFactor: ").append(toIndentedString(regularizationFactor)).append("\n");
    sb.append("    regularizationType: ").append(toIndentedString(regularizationType)).append("\n");
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


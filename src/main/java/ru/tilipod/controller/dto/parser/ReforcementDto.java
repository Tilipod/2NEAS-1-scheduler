package ru.tilipod.controller.dto.parser;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * ReforcementDto
 */
public class ReforcementDto   {
  @JsonProperty("countEpoch")
  private Integer countEpoch;

  @JsonProperty("epsilonStep")
  private Integer epsilonStep;

  @JsonProperty("errorClamp")
  private Double errorClamp;

  @JsonProperty("gamma")
  private Double gamma;

  @JsonProperty("minEpsilon")
  private Float minEpsilon;

  @JsonProperty("rewardFactor")
  private Double rewardFactor;

  @JsonProperty("updateStart")
  private Integer updateStart;

  public ReforcementDto countEpoch(Integer countEpoch) {
    this.countEpoch = countEpoch;
    return this;
  }

  /**
   * Кол-во эпох для обучения
   * @return countEpoch
  */
  @ApiModelProperty(value = "Кол-во эпох для обучения")


  public Integer getCountEpoch() {
    return countEpoch;
  }

  public void setCountEpoch(Integer countEpoch) {
    this.countEpoch = countEpoch;
  }

  public ReforcementDto epsilonStep(Integer epsilonStep) {
    this.epsilonStep = epsilonStep;
    return this;
  }

  /**
   * Знаменатель при вычислении случайного эпсилон
   * @return epsilonStep
  */
  @ApiModelProperty(value = "Знаменатель при вычислении случайного эпсилон")


  public Integer getEpsilonStep() {
    return epsilonStep;
  }

  public void setEpsilonStep(Integer epsilonStep) {
    this.epsilonStep = epsilonStep;
  }

  public ReforcementDto errorClamp(Double errorClamp) {
    this.errorClamp = errorClamp;
    return this;
  }

  /**
   * Допустимый разброс при накапливании вознаграждения
   * @return errorClamp
  */
  @ApiModelProperty(value = "Допустимый разброс при накапливании вознаграждения")


  public Double getErrorClamp() {
    return errorClamp;
  }

  public void setErrorClamp(Double errorClamp) {
    this.errorClamp = errorClamp;
  }

  public ReforcementDto gamma(Double gamma) {
    this.gamma = gamma;
    return this;
  }

  /**
   * Гамма из уравнения Белла
   * @return gamma
  */
  @ApiModelProperty(value = "Гамма из уравнения Белла")


  public Double getGamma() {
    return gamma;
  }

  public void setGamma(Double gamma) {
    this.gamma = gamma;
  }

  public ReforcementDto minEpsilon(Float minEpsilon) {
    this.minEpsilon = minEpsilon;
    return this;
  }

  /**
   * Минимальный эпсилон (см. про Exploration в RL)
   * @return minEpsilon
  */
  @ApiModelProperty(value = "Минимальный эпсилон (см. про Exploration в RL)")


  public Float getMinEpsilon() {
    return minEpsilon;
  }

  public void setMinEpsilon(Float minEpsilon) {
    this.minEpsilon = minEpsilon;
  }

  public ReforcementDto rewardFactor(Double rewardFactor) {
    this.rewardFactor = rewardFactor;
    return this;
  }

  /**
   * Коэффициент коррекции вознаграждения
   * @return rewardFactor
  */
  @ApiModelProperty(value = "Коэффициент коррекции вознаграждения")


  public Double getRewardFactor() {
    return rewardFactor;
  }

  public void setRewardFactor(Double rewardFactor) {
    this.rewardFactor = rewardFactor;
  }

  public ReforcementDto updateStart(Integer updateStart) {
    this.updateStart = updateStart;
    return this;
  }

  /**
   * Итерация, после которой происходит обновление нейронной сети (для RL)
   * @return updateStart
  */
  @ApiModelProperty(value = "Итерация, после которой происходит обновление нейронной сети (для RL)")


  public Integer getUpdateStart() {
    return updateStart;
  }

  public void setUpdateStart(Integer updateStart) {
    this.updateStart = updateStart;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReforcementDto reforcementDto = (ReforcementDto) o;
    return Objects.equals(this.countEpoch, reforcementDto.countEpoch) &&
        Objects.equals(this.epsilonStep, reforcementDto.epsilonStep) &&
        Objects.equals(this.errorClamp, reforcementDto.errorClamp) &&
        Objects.equals(this.gamma, reforcementDto.gamma) &&
        Objects.equals(this.minEpsilon, reforcementDto.minEpsilon) &&
        Objects.equals(this.rewardFactor, reforcementDto.rewardFactor) &&
        Objects.equals(this.updateStart, reforcementDto.updateStart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countEpoch, epsilonStep, errorClamp, gamma, minEpsilon, rewardFactor, updateStart);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReforcementDto {\n");
    
    sb.append("    countEpoch: ").append(toIndentedString(countEpoch)).append("\n");
    sb.append("    epsilonStep: ").append(toIndentedString(epsilonStep)).append("\n");
    sb.append("    errorClamp: ").append(toIndentedString(errorClamp)).append("\n");
    sb.append("    gamma: ").append(toIndentedString(gamma)).append("\n");
    sb.append("    minEpsilon: ").append(toIndentedString(minEpsilon)).append("\n");
    sb.append("    rewardFactor: ").append(toIndentedString(rewardFactor)).append("\n");
    sb.append("    updateStart: ").append(toIndentedString(updateStart)).append("\n");
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


package ru.tilipod.controller.dto.parser;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * LayerDto
 */
public class LayerDto   {
  /**
   * Функция активации для нейронов слоя
   */
  public enum ActivationTypeEnum {
    CUBE("CUBE"),
    
    ELU("ELU"),
    
    HARDSIGMOID("HARDSIGMOID"),
    
    HARDTANH("HARDTANH"),
    
    IDENTITY("IDENTITY"),
    
    LEAKYRELU("LEAKYRELU"),
    
    RATIONALTANH("RATIONALTANH"),
    
    RELU("RELU"),
    
    RRELU("RRELU"),
    
    SIGMOID("SIGMOID"),
    
    SOFTMAX("SOFTMAX"),
    
    SOFTPLUS("SOFTPLUS"),
    
    SOFTSIGN("SOFTSIGN"),
    
    TANH("TANH"),
    
    RECTIFIEDTANH("RECTIFIEDTANH"),
    
    SELU("SELU");

    private String value;

    ActivationTypeEnum(String value) {
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
    public static ActivationTypeEnum fromValue(String value) {
      for (ActivationTypeEnum b : ActivationTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("activationType")
  private ActivationTypeEnum activationType;

  @JsonProperty("countInput")
  private Integer countInput;

  @JsonProperty("countOutput")
  private Integer countOutput;

  @JsonProperty("kernelHeight")
  private Integer kernelHeight;

  @JsonProperty("kernelWeight")
  private Integer kernelWeight;

  @JsonProperty("layerNumber")
  private Integer layerNumber;

  /**
   * Тип слоя
   */
  public enum LayerTypeEnum {
    CONVOLUTIONAL("CONVOLUTIONAL"),
    
    BATCH_NORMALIZATION("BATCH_NORMALIZATION"),
    
    SUBSAMPLING("SUBSAMPLING"),
    
    DENSE("DENSE"),
    
    OUTPUT("OUTPUT");

    private String value;

    LayerTypeEnum(String value) {
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
    public static LayerTypeEnum fromValue(String value) {
      for (LayerTypeEnum b : LayerTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("layerType")
  private LayerTypeEnum layerType;

  /**
   * Тип пулинга
   */
  public enum PoolingTypeEnum {
    MAX("MAX"),
    
    AVG("AVG"),
    
    SUM("SUM"),
    
    PNORM("PNORM"),
    
    NONE("NONE");

    private String value;

    PoolingTypeEnum(String value) {
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
    public static PoolingTypeEnum fromValue(String value) {
      for (PoolingTypeEnum b : PoolingTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("poolingType")
  private PoolingTypeEnum poolingType;

  @JsonProperty("strideHeight")
  private Integer strideHeight;

  @JsonProperty("strideWeight")
  private Integer strideWeight;

  /**
   * Способ инициализации весов нейронов слоя
   */
  public enum WeightInitTypeEnum {
    DISTRIBUTION("DISTRIBUTION"),
    
    ZERO("ZERO"),
    
    SIGMOID_UNIFORM("SIGMOID_UNIFORM"),
    
    UNIFORM("UNIFORM"),
    
    XAVIER("XAVIER"),
    
    XAVIER_UNIFORM("XAVIER_UNIFORM"),
    
    XAVIER_FAN_IN("XAVIER_FAN_IN"),
    
    XAVIER_LEGACY("XAVIER_LEGACY"),
    
    RELU("RELU"),
    
    RELU_UNIFORM("RELU_UNIFORM");

    private String value;

    WeightInitTypeEnum(String value) {
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
    public static WeightInitTypeEnum fromValue(String value) {
      for (WeightInitTypeEnum b : WeightInitTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("weightInitType")
  private WeightInitTypeEnum weightInitType;

  public LayerDto activationType(ActivationTypeEnum activationType) {
    this.activationType = activationType;
    return this;
  }

  /**
   * Функция активации для нейронов слоя
   * @return activationType
  */
  @ApiModelProperty(value = "Функция активации для нейронов слоя")


  public ActivationTypeEnum getActivationType() {
    return activationType;
  }

  public void setActivationType(ActivationTypeEnum activationType) {
    this.activationType = activationType;
  }

  public LayerDto countInput(Integer countInput) {
    this.countInput = countInput;
    return this;
  }

  /**
   * Кол-во входов
   * @return countInput
  */
  @ApiModelProperty(value = "Кол-во входов")


  public Integer getCountInput() {
    return countInput;
  }

  public void setCountInput(Integer countInput) {
    this.countInput = countInput;
  }

  public LayerDto countOutput(Integer countOutput) {
    this.countOutput = countOutput;
    return this;
  }

  /**
   * Кол-во выходов
   * @return countOutput
  */
  @ApiModelProperty(required = true, value = "Кол-во выходов")
  @NotNull


  public Integer getCountOutput() {
    return countOutput;
  }

  public void setCountOutput(Integer countOutput) {
    this.countOutput = countOutput;
  }

  public LayerDto kernelHeight(Integer kernelHeight) {
    this.kernelHeight = kernelHeight;
    return this;
  }

  /**
   * Высота фильтра
   * @return kernelHeight
  */
  @ApiModelProperty(value = "Высота фильтра")


  public Integer getKernelHeight() {
    return kernelHeight;
  }

  public void setKernelHeight(Integer kernelHeight) {
    this.kernelHeight = kernelHeight;
  }

  public LayerDto kernelWeight(Integer kernelWeight) {
    this.kernelWeight = kernelWeight;
    return this;
  }

  /**
   * Ширина фильтра
   * @return kernelWeight
  */
  @ApiModelProperty(value = "Ширина фильтра")


  public Integer getKernelWeight() {
    return kernelWeight;
  }

  public void setKernelWeight(Integer kernelWeight) {
    this.kernelWeight = kernelWeight;
  }

  public LayerDto layerNumber(Integer layerNumber) {
    this.layerNumber = layerNumber;
    return this;
  }

  /**
   * Номер слоя
   * @return layerNumber
  */
  @ApiModelProperty(required = true, value = "Номер слоя")
  @NotNull


  public Integer getLayerNumber() {
    return layerNumber;
  }

  public void setLayerNumber(Integer layerNumber) {
    this.layerNumber = layerNumber;
  }

  public LayerDto layerType(LayerTypeEnum layerType) {
    this.layerType = layerType;
    return this;
  }

  /**
   * Тип слоя
   * @return layerType
  */
  @ApiModelProperty(required = true, value = "Тип слоя")
  @NotNull


  public LayerTypeEnum getLayerType() {
    return layerType;
  }

  public void setLayerType(LayerTypeEnum layerType) {
    this.layerType = layerType;
  }

  public LayerDto poolingType(PoolingTypeEnum poolingType) {
    this.poolingType = poolingType;
    return this;
  }

  /**
   * Тип пулинга
   * @return poolingType
  */
  @ApiModelProperty(value = "Тип пулинга")


  public PoolingTypeEnum getPoolingType() {
    return poolingType;
  }

  public void setPoolingType(PoolingTypeEnum poolingType) {
    this.poolingType = poolingType;
  }

  public LayerDto strideHeight(Integer strideHeight) {
    this.strideHeight = strideHeight;
    return this;
  }

  /**
   * Высота шага
   * @return strideHeight
  */
  @ApiModelProperty(value = "Высота шага")


  public Integer getStrideHeight() {
    return strideHeight;
  }

  public void setStrideHeight(Integer strideHeight) {
    this.strideHeight = strideHeight;
  }

  public LayerDto strideWeight(Integer strideWeight) {
    this.strideWeight = strideWeight;
    return this;
  }

  /**
   * Ширина шага
   * @return strideWeight
  */
  @ApiModelProperty(value = "Ширина шага")


  public Integer getStrideWeight() {
    return strideWeight;
  }

  public void setStrideWeight(Integer strideWeight) {
    this.strideWeight = strideWeight;
  }

  public LayerDto weightInitType(WeightInitTypeEnum weightInitType) {
    this.weightInitType = weightInitType;
    return this;
  }

  /**
   * Способ инициализации весов нейронов слоя
   * @return weightInitType
  */
  @ApiModelProperty(value = "Способ инициализации весов нейронов слоя")


  public WeightInitTypeEnum getWeightInitType() {
    return weightInitType;
  }

  public void setWeightInitType(WeightInitTypeEnum weightInitType) {
    this.weightInitType = weightInitType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LayerDto layerDto = (LayerDto) o;
    return Objects.equals(this.activationType, layerDto.activationType) &&
        Objects.equals(this.countInput, layerDto.countInput) &&
        Objects.equals(this.countOutput, layerDto.countOutput) &&
        Objects.equals(this.kernelHeight, layerDto.kernelHeight) &&
        Objects.equals(this.kernelWeight, layerDto.kernelWeight) &&
        Objects.equals(this.layerNumber, layerDto.layerNumber) &&
        Objects.equals(this.layerType, layerDto.layerType) &&
        Objects.equals(this.poolingType, layerDto.poolingType) &&
        Objects.equals(this.strideHeight, layerDto.strideHeight) &&
        Objects.equals(this.strideWeight, layerDto.strideWeight) &&
        Objects.equals(this.weightInitType, layerDto.weightInitType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activationType, countInput, countOutput, kernelHeight, kernelWeight, layerNumber, layerType, poolingType, strideHeight, strideWeight, weightInitType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LayerDto {\n");
    
    sb.append("    activationType: ").append(toIndentedString(activationType)).append("\n");
    sb.append("    countInput: ").append(toIndentedString(countInput)).append("\n");
    sb.append("    countOutput: ").append(toIndentedString(countOutput)).append("\n");
    sb.append("    kernelHeight: ").append(toIndentedString(kernelHeight)).append("\n");
    sb.append("    kernelWeight: ").append(toIndentedString(kernelWeight)).append("\n");
    sb.append("    layerNumber: ").append(toIndentedString(layerNumber)).append("\n");
    sb.append("    layerType: ").append(toIndentedString(layerType)).append("\n");
    sb.append("    poolingType: ").append(toIndentedString(poolingType)).append("\n");
    sb.append("    strideHeight: ").append(toIndentedString(strideHeight)).append("\n");
    sb.append("    strideWeight: ").append(toIndentedString(strideWeight)).append("\n");
    sb.append("    weightInitType: ").append(toIndentedString(weightInitType)).append("\n");
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


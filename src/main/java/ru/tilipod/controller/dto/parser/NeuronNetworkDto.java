package ru.tilipod.controller.dto.parser;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NeuronNetworkDto
 */
public class NeuronNetworkDto   {
  @JsonProperty("backprop")
  private Boolean backprop;

  @JsonProperty("inputType")
  private InputTypeDto inputType;

  @JsonProperty("iterations")
  private Integer iterations;

  @JsonProperty("layers")
  @Valid
  private List<LayerDto> layers = null;

  @JsonProperty("learningRate")
  private Double learningRate;

  /**
   * Алгоритм обучения
   */
  public enum OptimizationAlgoEnum {
    LINE_GRADIENT_DESCENT("LINE_GRADIENT_DESCENT"),
    
    CONJUGATE_GRADIENT("CONJUGATE_GRADIENT"),
    
    HESSIAN_FREE("HESSIAN_FREE"),
    
    LBFGS("LBFGS"),
    
    STOCHASTIC_GRADIENT_DESCENT("STOCHASTIC_GRADIENT_DESCENT");

    private String value;

    OptimizationAlgoEnum(String value) {
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
    public static OptimizationAlgoEnum fromValue(String value) {
      for (OptimizationAlgoEnum b : OptimizationAlgoEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("optimizationAlgo")
  private OptimizationAlgoEnum optimizationAlgo;

  @JsonProperty("pathToSave")
  private String pathToSave;

  @JsonProperty("pretrain")
  private Boolean pretrain;

  @JsonProperty("reforcement")
  private ReforcementDto reforcement;

  @JsonProperty("regularization")
  private RegularizationDto regularization;

  @JsonProperty("seed")
  private Integer seed;

  @JsonProperty("taskId")
  private Integer taskId;

  @JsonProperty("updater")
  private UpdaterDto updater;

  /**
   * Правило инициализации весов
   */
  public enum WeightInitEnum {
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

    WeightInitEnum(String value) {
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
    public static WeightInitEnum fromValue(String value) {
      for (WeightInitEnum b : WeightInitEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("weightInit")
  private WeightInitEnum weightInit;

  public NeuronNetworkDto backprop(Boolean backprop) {
    this.backprop = backprop;
    return this;
  }

  /**
   * Использовать обратное распространение ошибки
   * @return backprop
  */
  @ApiModelProperty(value = "Использовать обратное распространение ошибки")


  public Boolean getBackprop() {
    return backprop;
  }

  public void setBackprop(Boolean backprop) {
    this.backprop = backprop;
  }

  public NeuronNetworkDto inputType(InputTypeDto inputType) {
    this.inputType = inputType;
    return this;
  }

  /**
   * Get inputType
   * @return inputType
  */
  @ApiModelProperty(value = "")

  @Valid

  public InputTypeDto getInputType() {
    return inputType;
  }

  public void setInputType(InputTypeDto inputType) {
    this.inputType = inputType;
  }

  public NeuronNetworkDto iterations(Integer iterations) {
    this.iterations = iterations;
    return this;
  }

  /**
   * Кол-во итераций на каждой эпохе обучения
   * @return iterations
  */
  @ApiModelProperty(value = "Кол-во итераций на каждой эпохе обучения")


  public Integer getIterations() {
    return iterations;
  }

  public void setIterations(Integer iterations) {
    this.iterations = iterations;
  }

  public NeuronNetworkDto layers(List<LayerDto> layers) {
    this.layers = layers;
    return this;
  }

  public NeuronNetworkDto addLayersItem(LayerDto layersItem) {
    if (this.layers == null) {
      this.layers = new ArrayList<>();
    }
    this.layers.add(layersItem);
    return this;
  }

  /**
   * Настройки слоев сети
   * @return layers
  */
  @ApiModelProperty(value = "Настройки слоев сети")

  @Valid

  public List<LayerDto> getLayers() {
    return layers;
  }

  public void setLayers(List<LayerDto> layers) {
    this.layers = layers;
  }

  public NeuronNetworkDto learningRate(Double learningRate) {
    this.learningRate = learningRate;
    return this;
  }

  /**
   * Скорость обучения
   * @return learningRate
  */
  @ApiModelProperty(value = "Скорость обучения")


  public Double getLearningRate() {
    return learningRate;
  }

  public void setLearningRate(Double learningRate) {
    this.learningRate = learningRate;
  }

  public NeuronNetworkDto optimizationAlgo(OptimizationAlgoEnum optimizationAlgo) {
    this.optimizationAlgo = optimizationAlgo;
    return this;
  }

  /**
   * Алгоритм обучения
   * @return optimizationAlgo
  */
  @ApiModelProperty(value = "Алгоритм обучения")


  public OptimizationAlgoEnum getOptimizationAlgo() {
    return optimizationAlgo;
  }

  public void setOptimizationAlgo(OptimizationAlgoEnum optimizationAlgo) {
    this.optimizationAlgo = optimizationAlgo;
  }

  public NeuronNetworkDto pathToSave(String pathToSave) {
    this.pathToSave = pathToSave;
    return this;
  }

  /**
   * Путь к файлу сохранения модели сети
   * @return pathToSave
  */
  @ApiModelProperty(required = true, value = "Путь к файлу сохранения модели сети")
  @NotNull


  public String getPathToSave() {
    return pathToSave;
  }

  public void setPathToSave(String pathToSave) {
    this.pathToSave = pathToSave;
  }

  public NeuronNetworkDto pretrain(Boolean pretrain) {
    this.pretrain = pretrain;
    return this;
  }

  /**
   * Признак предварительного обучения
   * @return pretrain
  */
  @ApiModelProperty(value = "Признак предварительного обучения")


  public Boolean getPretrain() {
    return pretrain;
  }

  public void setPretrain(Boolean pretrain) {
    this.pretrain = pretrain;
  }

  public NeuronNetworkDto reforcement(ReforcementDto reforcement) {
    this.reforcement = reforcement;
    return this;
  }

  /**
   * Get reforcement
   * @return reforcement
  */
  @ApiModelProperty(value = "")

  @Valid

  public ReforcementDto getReforcement() {
    return reforcement;
  }

  public void setReforcement(ReforcementDto reforcement) {
    this.reforcement = reforcement;
  }

  public NeuronNetworkDto regularization(RegularizationDto regularization) {
    this.regularization = regularization;
    return this;
  }

  /**
   * Get regularization
   * @return regularization
  */
  @ApiModelProperty(value = "")

  @Valid

  public RegularizationDto getRegularization() {
    return regularization;
  }

  public void setRegularization(RegularizationDto regularization) {
    this.regularization = regularization;
  }

  public NeuronNetworkDto seed(Integer seed) {
    this.seed = seed;
    return this;
  }

  /**
   * Число-инициализатор для генератора случайных чисел
   * @return seed
  */
  @ApiModelProperty(value = "Число-инициализатор для генератора случайных чисел")


  public Integer getSeed() {
    return seed;
  }

  public void setSeed(Integer seed) {
    this.seed = seed;
  }

  public NeuronNetworkDto taskId(Integer taskId) {
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

  public NeuronNetworkDto updater(UpdaterDto updater) {
    this.updater = updater;
    return this;
  }

  /**
   * Get updater
   * @return updater
  */
  @ApiModelProperty(value = "")

  @Valid

  public UpdaterDto getUpdater() {
    return updater;
  }

  public void setUpdater(UpdaterDto updater) {
    this.updater = updater;
  }

  public NeuronNetworkDto weightInit(WeightInitEnum weightInit) {
    this.weightInit = weightInit;
    return this;
  }

  /**
   * Правило инициализации весов
   * @return weightInit
  */
  @ApiModelProperty(value = "Правило инициализации весов")


  public WeightInitEnum getWeightInit() {
    return weightInit;
  }

  public void setWeightInit(WeightInitEnum weightInit) {
    this.weightInit = weightInit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NeuronNetworkDto neuronNetworkDto = (NeuronNetworkDto) o;
    return Objects.equals(this.backprop, neuronNetworkDto.backprop) &&
        Objects.equals(this.inputType, neuronNetworkDto.inputType) &&
        Objects.equals(this.iterations, neuronNetworkDto.iterations) &&
        Objects.equals(this.layers, neuronNetworkDto.layers) &&
        Objects.equals(this.learningRate, neuronNetworkDto.learningRate) &&
        Objects.equals(this.optimizationAlgo, neuronNetworkDto.optimizationAlgo) &&
        Objects.equals(this.pathToSave, neuronNetworkDto.pathToSave) &&
        Objects.equals(this.pretrain, neuronNetworkDto.pretrain) &&
        Objects.equals(this.reforcement, neuronNetworkDto.reforcement) &&
        Objects.equals(this.regularization, neuronNetworkDto.regularization) &&
        Objects.equals(this.seed, neuronNetworkDto.seed) &&
        Objects.equals(this.taskId, neuronNetworkDto.taskId) &&
        Objects.equals(this.updater, neuronNetworkDto.updater) &&
        Objects.equals(this.weightInit, neuronNetworkDto.weightInit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(backprop, inputType, iterations, layers, learningRate, optimizationAlgo, pathToSave, pretrain, reforcement, regularization, seed, taskId, updater, weightInit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NeuronNetworkDto {\n");
    
    sb.append("    backprop: ").append(toIndentedString(backprop)).append("\n");
    sb.append("    inputType: ").append(toIndentedString(inputType)).append("\n");
    sb.append("    iterations: ").append(toIndentedString(iterations)).append("\n");
    sb.append("    layers: ").append(toIndentedString(layers)).append("\n");
    sb.append("    learningRate: ").append(toIndentedString(learningRate)).append("\n");
    sb.append("    optimizationAlgo: ").append(toIndentedString(optimizationAlgo)).append("\n");
    sb.append("    pathToSave: ").append(toIndentedString(pathToSave)).append("\n");
    sb.append("    pretrain: ").append(toIndentedString(pretrain)).append("\n");
    sb.append("    reforcement: ").append(toIndentedString(reforcement)).append("\n");
    sb.append("    regularization: ").append(toIndentedString(regularization)).append("\n");
    sb.append("    seed: ").append(toIndentedString(seed)).append("\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    updater: ").append(toIndentedString(updater)).append("\n");
    sb.append("    weightInit: ").append(toIndentedString(weightInit)).append("\n");
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


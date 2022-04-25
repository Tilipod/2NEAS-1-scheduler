package ru.tilipod.feign.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tilipod.controller.dto.teacher.TrainingDto;

import javax.validation.Valid;

@Validated
@Api(value = "training", description = "the teacher API")
public interface TeacherApi {

    /**
     * POST /training/training/step : Провести обучение нейронной сети
     *
     * @param trainingDto trainingDto (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "Провести обучение нейронной сети", nickname = "stepTrainingUsingPOST", notes = "", tags={ "training-controller", })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 201, message = "Created"),
                           @ApiResponse(code = 401, message = "Unauthorized"),
                           @ApiResponse(code = 403, message = "Forbidden"),
                           @ApiResponse(code = 404, message = "Not Found") })
    @PostMapping(value = "/training/training/step", consumes = { "application/json" })
    ResponseEntity<Void> stepTrainingUsingPOST(@ApiParam(value = "trainingDto", required=true)  @Valid @RequestBody TrainingDto trainingDto);

}

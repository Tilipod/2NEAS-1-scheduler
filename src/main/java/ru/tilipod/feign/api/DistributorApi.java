package ru.tilipod.feign.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tilipod.controller.dto.distributor.CloudImagesDownloadRequest;

import javax.validation.Valid;

@Validated
@Api(value = "dataset", description = "the distributor API")
public interface DistributorApi {

    /**
     * POST /dataset/cloud/images/download : Выгрузить изображения с облака на диск
     *
     * @param request request (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "Выгрузить изображения с облака на диск", nickname = "downloadImagesFromCloudUsingPOST", tags={ "dataset-controller", })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 201, message = "Created"),
                           @ApiResponse(code = 401, message = "Unauthorized"),
                           @ApiResponse(code = 403, message = "Forbidden"),
                           @ApiResponse(code = 404, message = "Not Found") })
    @PostMapping(value = "/dataset/cloud/images/download", consumes = { "application/json" })
    ResponseEntity<Void> downloadImagesFromCloudUsingPOST(@ApiParam(value = "request", required=true)  @Valid @RequestBody CloudImagesDownloadRequest request);

}

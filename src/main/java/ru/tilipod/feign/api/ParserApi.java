package ru.tilipod.feign.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tilipod.controller.dto.parser.NeuronNetworkDto;

import javax.validation.Valid;

@Validated
@Api(value = "parsing", description = "the parser API")
public interface ParserApi {

    /**
     * POST /parsing/parsing : Воспроизвести структуру нейронной сети по запросу клиента
     *
     * @param neuronNetworkDto neuronNetworkDto (required)
     * @param withMentoring withMentoring (optional, default to false)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "Воспроизвести структуру нейронной сети по запросу клиента", nickname = "parseNeuronNetworkUsingPOST", tags={ "parsing-controller" })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 201, message = "Created"),
                           @ApiResponse(code = 401, message = "Unauthorized"),
                           @ApiResponse(code = 403, message = "Forbidden"),
                           @ApiResponse(code = 404, message = "Not Found") })
    @PostMapping(value = "/parsing/parsing", consumes = { "application/json" })
    ResponseEntity<Void> parseNeuronNetworkUsingPOST(@ApiParam(value = "neuronNetworkDto", required=true)  @Valid @RequestBody NeuronNetworkDto neuronNetworkDto,
                                                     @ApiParam(value = "withMentoring", defaultValue = "false") @Valid @RequestParam(value = "withMentoring", required = false, defaultValue="false") Boolean withMentoring);

}

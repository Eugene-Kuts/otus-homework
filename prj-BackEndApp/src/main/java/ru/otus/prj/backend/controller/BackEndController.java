package ru.otus.prj.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.prj.backend.request.BackEndRequest;
import ru.otus.prj.backend.response.BackEndResponse;
import ru.otus.prj.backend.controller.exceptions.NullValueParameterException;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value="Back end operation", description="Operation of multiplication for incoming numbers")
public class BackEndController {

    @ApiOperation(value = "Operation to get multiplication of incoming parameters", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully multiplication"),
            @ApiResponse(code = 404, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Something's wrong")})
    @RequestMapping(path = "/getMultiplication", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BackEndResponse> getBackEndResponse(@RequestBody BackEndRequest backEndRequest) throws NullValueParameterException {
        log.info("backEndRequest= " + backEndRequest);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(new BackEndResponse((backEndRequest.getParamA()*backEndRequest.getParamB()*backEndRequest.getParamC())));
            log.info("json= " + json);
            return new ResponseEntity(json, HttpStatus.OK);
        } catch (NullPointerException npe){
            throw new NullValueParameterException();
        } catch(JsonProcessingException ex) {
            return new ResponseEntity("Exception: " + ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package ru.otus.prj.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class BackEndController {

    @RequestMapping(path = "/", produces = "application/json", consumes = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getBackEndResponse(@RequestBody BackEndRequest backEndRequest) throws NullValueParameterException {
        log.info("backEndRequest= " + backEndRequest);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(new BackEndResponse(backEndRequest.getMultiplication()));
            log.info("json= " + json);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (NullPointerException npe){
            throw new NullValueParameterException();
        } catch(JsonProcessingException ex) {
            return new ResponseEntity("Exception: " + ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

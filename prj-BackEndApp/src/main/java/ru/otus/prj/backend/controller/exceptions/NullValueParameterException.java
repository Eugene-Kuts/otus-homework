package ru.otus.prj.backend.controller.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request parameters must not be null")
public class NullValueParameterException extends Exception {
    public NullValueParameterException() {
        log.error("Request parameters must not be null");
    }
}

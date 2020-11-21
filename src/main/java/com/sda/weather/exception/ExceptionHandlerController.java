package com.sda.weather.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(BadrequestException.class)
    @ResponseStatus()
    void badRequestHandler(BadrequestException exception) {
        log.error(exception.getMessage());
    }
}

package com.sda.weather.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus() // todo specify status code HttpStatus.BAD_REQUEST
    void badRequestHandler(BadRequestException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus() // todo specify status code
    void notFoundExceptionHandler(NotFoundException exception) {
        log.error(exception.getMessage());
    }
}

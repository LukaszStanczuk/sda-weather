package com.sda.weather.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
        // todo specify status code HttpStatus.BAD_REQUEST ok
    void badRequestHandler(BadRequestException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
        // todo specify status code ok
    void notFoundExceptionHandler(NotFoundException exception) {
        log.error(exception.getMessage());
    }
}

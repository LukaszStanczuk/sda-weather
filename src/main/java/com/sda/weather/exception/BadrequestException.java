package com.sda.weather.exception;

public class BadrequestException extends RuntimeException {
    public BadrequestException(String message) {
        super("Bad request" + message);
    }
}

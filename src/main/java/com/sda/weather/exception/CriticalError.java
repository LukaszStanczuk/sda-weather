package com.sda.weather.exception;

public class CriticalError extends NullPointerException {
    public CriticalError(String message) {
        super(message);
    }
}

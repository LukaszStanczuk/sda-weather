package com.sda.weather.exception;

public class WrongDataException extends NullPointerException {
    public WrongDataException(String message) {
        super(message);
    }
}

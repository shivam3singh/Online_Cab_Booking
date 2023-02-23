package com.urbancab.exception;

public class LogInException extends RuntimeException{
    public LogInException() {
    }

    public LogInException(String message) {
        super(message);
    }
}

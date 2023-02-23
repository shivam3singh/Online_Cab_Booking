package com.urbancab.exception;

public class LogOutException extends RuntimeException{
    public LogOutException() {
    }

    public LogOutException(String message) {
        super(message);
    }
}

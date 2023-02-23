package com.urbancab.exception;

public class TripBookingException extends RuntimeException{
    public TripBookingException() {
    }

    public TripBookingException(String message) {
        super(message);
    }
}

package com.epam.cinema.exception;



public class EventAlreadyExistException extends RuntimeException {
    public EventAlreadyExistException(String message) {
        super(message);
    }
}


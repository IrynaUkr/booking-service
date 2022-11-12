package com.epam.cinema.exception;



public class EventNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Event not found";

    public EventNotFoundException(String message) {
        super(DEFAULT_MESSAGE);
    }
}


package com.epam.cinema.exception;



public class UserAccountAlreadyExistException extends RuntimeException {
    public UserAccountAlreadyExistException(String message) {
        super(message);
    }
}

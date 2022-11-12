package com.epam.cinema.exception;



public class BalanceNotEnoughMoneyException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "It is not enough money for booking ticket in your account";

    public BalanceNotEnoughMoneyException(String message) {
        super(DEFAULT_MESSAGE);
    }
}


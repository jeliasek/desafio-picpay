package com.desafiopicpay.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User Not Found.");
    }
}

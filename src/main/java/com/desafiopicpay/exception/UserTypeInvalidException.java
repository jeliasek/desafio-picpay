package com.desafiopicpay.exception;

public class UserTypeInvalidException extends RuntimeException{
    public UserTypeInvalidException() {
        super("User type 'MERCHANT' is not allowed to send a transaction.");
    }
}

package com.desafiopicpay.exception;

public class NotEnoughBalanceException extends RuntimeException{
    public NotEnoughBalanceException() {
        super("User doesn't have enough balance.");
    }
}

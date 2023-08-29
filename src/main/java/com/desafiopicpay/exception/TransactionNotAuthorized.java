package com.desafiopicpay.exception;

public class TransactionNotAuthorized extends RuntimeException{
    public TransactionNotAuthorized() {
        super("Transaction not authorized.");
    }
}

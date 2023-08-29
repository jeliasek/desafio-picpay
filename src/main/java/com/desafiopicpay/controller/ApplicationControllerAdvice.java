package com.desafiopicpay.controller;

import com.desafiopicpay.exception.NotEnoughBalanceException;
import com.desafiopicpay.exception.TransactionNotAuthorized;
import com.desafiopicpay.exception.UserNotFoundException;
import com.desafiopicpay.exception.UserTypeInvalidException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(NotEnoughBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleNotEnoughBalanceException(NotEnoughBalanceException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleUserNotFoundException(UserNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(UserTypeInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleUserTypeInvalidException(UserTypeInvalidException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(TransactionNotAuthorized.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleTransactionNotAuthorized(TransactionNotAuthorized ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream().map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleDuplicateEntry(DataIntegrityViolationException ex) {
        ApiErrors error = new ApiErrors("User already registered");
        return error;
    }
}

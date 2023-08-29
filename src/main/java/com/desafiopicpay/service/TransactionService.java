package com.desafiopicpay.service;

import com.desafiopicpay.domain.transaction.Transaction;
import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dto.TransactionDto;
import com.desafiopicpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    UserService userService;

    @Autowired
    TransactionRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NotificationService notificationService;

    public Transaction createTransaction(TransactionDto transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.sender());
        User receiver = this.userService.findUserById(transaction.receiver());

        userService.validateTransaction(sender, transaction.amount());

        boolean isAuhorized = this.isAuthorizedTransaction(sender, transaction.amount());
        if (!isAuhorized) {
            throw new Exception("Transaction not authorized.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setDate(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        receiver.setBalance(receiver.getBalance().add(transaction.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

//        this.notificationService.sendNotification(sender, "Transaction sent successfully.");
//        this.notificationService.sendNotification(receiver, "Transaction received successfully.");
        System.out.println("Notify sent to users");
        return newTransaction;
    }

    public boolean isAuthorizedTransaction(User sender, BigDecimal amount) {
        ResponseEntity<Map> authorizationResponse = this.restTemplate.getForEntity
                ("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String)authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;


    }
}

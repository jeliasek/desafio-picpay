package com.desafiopicpay.service;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.domain.user.UserType;
import com.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal value) throws Exception{
        if (sender.getType() == UserType.MERCHANT)
            throw new Exception("User type 'MERCHANT' is not allowed to make a transaction.");

        if (sender.getBalance().compareTo(value) < 0)
            throw new Exception("User doesn't have enough balance.");
    }

    public User findUserById(Integer id) throws Exception{
        return this.repository.findUserById(id)
                .orElseThrow(() -> new Exception("User not found."));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }
}

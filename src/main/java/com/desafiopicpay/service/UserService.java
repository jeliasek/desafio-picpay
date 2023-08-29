package com.desafiopicpay.service;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.domain.user.UserType;
import com.desafiopicpay.dto.UserDto;
import com.desafiopicpay.exception.NotEnoughBalanceException;
import com.desafiopicpay.exception.UserNotFoundException;
import com.desafiopicpay.exception.UserTypeInvalidException;
import com.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal value) throws Exception{
        if (sender.getType() == UserType.MERCHANT)
            throw new UserTypeInvalidException();

        if (sender.getBalance().compareTo(value) < 0)
            throw new NotEnoughBalanceException();
    }

    public User findUserById(Integer id) throws Exception{
        return this.repository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException());
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDto user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
       return this.repository.findAll();
    }

    public User updateUser(Integer id, UserDto data) {
        User user = repository.findUserById(id).orElseThrow(() -> new UserNotFoundException());
        user.setEmail(data.email());
        user.setBalance(data.balance());
        user.setPassword(data.password());
        user.setDocument(data.document());
        user.setType(data.type());
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        this.saveUser(user);
        return user;
    }
}

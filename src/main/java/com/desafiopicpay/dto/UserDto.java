package com.desafiopicpay.dto;

import com.desafiopicpay.domain.user.UserType;

import java.math.BigDecimal;

public record UserDto(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType type) {
}

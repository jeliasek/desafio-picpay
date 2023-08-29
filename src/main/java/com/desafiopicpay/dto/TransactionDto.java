package com.desafiopicpay.dto;

import java.math.BigDecimal;

public record TransactionDto (BigDecimal amount, Integer sender, Integer receiver) {
}

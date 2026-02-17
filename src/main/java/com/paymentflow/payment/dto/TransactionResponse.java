package com.paymentflow.payment.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {

    private Long id;
    private String senderName;
    private String receiverName;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}

package com.paymentflow.payment.mapper;

import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.entity.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransferRequest mapEntityToRequest(Transaction transaction) {
        TransferRequest dto = new TransferRequest();
        dto.setSenderId(transaction.getSender().getId());
        dto.setReceiverId(transaction.getReceiver().getId());
        dto.setAmount(transaction.getAmount());
        return dto;
    }

}

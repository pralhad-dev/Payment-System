package com.paymentflow.payment.mapper;

import com.paymentflow.payment.dto.TransactionResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.entity.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {

    public TransferRequest mapEntityToRequest(Transaction transaction) {
        TransferRequest dto = new TransferRequest();
        dto.setSenderId(transaction.getSender().getId());
        dto.setReceiverId(transaction.getReceiver().getId());
        dto.setAmount(transaction.getAmount());
        return dto;
    }

    public TransactionResponse mapEntityToResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setSenderName(transaction.getSender().getName());
        response.setReceiverName(transaction.getReceiver().getName());
        response.setAmount(transaction.getAmount());
        response.setStatus(transaction.getStatus().name());
        response.setCreatedAt(transaction.getCreatedAt());

        return response;
    }

    public List<TransactionResponse> mapAllTxnListToResponse(List<Transaction> transaction) {
        List<TransactionResponse> responses = new ArrayList<>();
        for (Transaction txn : transaction){
            TransactionResponse txnResponse = new TransactionResponse();
            txnResponse.setId(txn.getId());
            txnResponse.setSenderName(txn.getSender().getName());
            txnResponse.setReceiverName(txn.getReceiver().getName());
            txnResponse.setAmount(txn.getAmount());
            txnResponse.setStatus(txn.getStatus().name());
            txnResponse.setCreatedAt(txn.getCreatedAt());

            responses.add(txnResponse);
        }
        return responses;
    }
}

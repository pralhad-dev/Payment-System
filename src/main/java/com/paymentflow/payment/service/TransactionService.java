package com.paymentflow.payment.service;

import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransactionResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.entity.Transaction;
import com.paymentflow.payment.exception.DataNotFoundException;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface TransactionService {
     GlobalApiResponse<TransferRequest> transferMoney(TransferRequest request) throws DataNotFoundException;

     GlobalApiResponse<TransactionResponse> fetchTransactionsById(Long id) throws DataNotFoundException;

     GlobalApiResponse<List<TransactionResponse>> getAllTxn();

     GlobalApiResponse<List<TransactionResponse>> getAllTxnByUserId(Long id) throws DataNotFoundException;

     GlobalApiResponse<TransactionResponse> reverseTxn(Long txnId) throws DataNotFoundException;

     Transaction retryTransaction(Long transactionId) throws DataNotFoundException;
}

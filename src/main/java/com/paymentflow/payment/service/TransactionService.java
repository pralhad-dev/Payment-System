package com.paymentflow.payment.service;

import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransactionResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.exception.DataNotFoundException;

import java.util.List;

public interface TransactionService {
     GlobalApiResponse<TransferRequest> transferMoney(TransferRequest request) throws DataNotFoundException;

     GlobalApiResponse<TransactionResponse> fetchTransactionsById(Long id) throws DataNotFoundException;

     GlobalApiResponse<List<TransactionResponse>> getAllTxn();
}

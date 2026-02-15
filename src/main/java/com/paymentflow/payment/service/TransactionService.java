package com.paymentflow.payment.service;

import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransferRequest;

public interface TransactionService {
     GlobalApiResponse transferMoney(TransferRequest request);
}

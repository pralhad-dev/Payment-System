package com.paymentflow.payment.service;

import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.exception.DataNotFoundException;

public interface TransactionService {
     GlobalApiResponse<TransferRequest> transferMoney(TransferRequest request) throws DataNotFoundException;
}

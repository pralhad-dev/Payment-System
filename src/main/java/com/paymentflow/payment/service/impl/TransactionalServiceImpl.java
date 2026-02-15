package com.paymentflow.payment.service.impl;

import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.repository.TransactionRepository;
import com.paymentflow.payment.repository.UserRepository;
import com.paymentflow.payment.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class TransactionalServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionalServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public GlobalApiResponse transferMoney(TransferRequest request) {
        GlobalApiResponse response = new GlobalApiResponse();
        log.info("Processing transfer request: From {} To {} Amount: {}",
                request.getSenderId(), request.getReceiverId(), request.getAmount());

        return null;
    }
}

package com.paymentflow.payment.service.impl;

import com.paymentflow.payment.Enum.ResponseStatus;
import com.paymentflow.payment.Enum.TransactionStatus;
import com.paymentflow.payment.dto.CustomStatus;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransactionResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.entity.Transaction;
import com.paymentflow.payment.entity.User;
import com.paymentflow.payment.exception.DataNotFoundException;
import com.paymentflow.payment.exception.InsufficientBalanceException;
import com.paymentflow.payment.mapper.TransactionMapper;
import com.paymentflow.payment.repository.TransactionRepository;
import com.paymentflow.payment.repository.UserRepository;
import com.paymentflow.payment.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.CookieStore;
import java.util.List;


@Service
@Slf4j
public class TransactionalServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;

    public TransactionalServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public GlobalApiResponse<TransferRequest> transferMoney(TransferRequest request)
            throws DataNotFoundException {

        GlobalApiResponse<TransferRequest> response = new GlobalApiResponse<>();
        if (request.getSenderId().equals(request.getReceiverId())) {
            throw new IllegalArgumentException("Sender and Receiver must be different");
        }

        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new DataNotFoundException(CustomStatus.DATA_NOT_FOUND, CustomStatus.DATA_NOT_FOUND_CODE));

        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new DataNotFoundException(CustomStatus.DATA_NOT_FOUND, CustomStatus.DATA_NOT_FOUND_CODE));

        BigDecimal transferAmount = request.getAmount();

        if (sender.getBalance().compareTo(transferAmount) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance", CustomStatus.SUCCESS_CODE);
        }

        sender.setBalance(sender.getBalance().subtract(transferAmount));
        receiver.setBalance(receiver.getBalance().add(transferAmount));

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(transferAmount);
        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);

        TransferRequest dto = transactionMapper.mapEntityToRequest(transaction);

        response.setResponseData(dto);
        response.setResponseCode(CustomStatus.CREATED_CODE);
        response.setResponseMsg(CustomStatus.TRANSFER_SUCCESS_MSG);
        response.setStatus(ResponseStatus.SUCCESS);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public GlobalApiResponse<TransactionResponse> fetchTransactionsById(Long id) throws DataNotFoundException {
        GlobalApiResponse<TransactionResponse> response = new GlobalApiResponse<>();
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                                CustomStatus.DATA_NOT_FOUND,
                                CustomStatus.DATA_NOT_FOUND_CODE
                        )
                );

        TransactionResponse data = transactionMapper.mapEntityToResponse(transaction);
        response.setResponseData(data);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);
        response.setResponseMsg(CustomStatus.RETRIEVE_SUCCESS_MSG);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public GlobalApiResponse<List<TransactionResponse>> getAllTxn(){
        GlobalApiResponse<List<TransactionResponse>> response  = new GlobalApiResponse<>();

        List<Transaction> transaction = transactionRepository.findAll();
        List<TransactionResponse> txnResponse =transactionMapper.mapAllTxnListToResponse(transaction);
        response.setResponseData(txnResponse);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);
        response.setResponseMsg(CustomStatus.RETRIEVE_SUCCESS_MSG);
        response.setTotalRecords(txnResponse.size());
        return response;
    }

}

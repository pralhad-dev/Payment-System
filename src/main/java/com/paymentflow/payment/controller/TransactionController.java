package com.paymentflow.payment.controller;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.exception.DataNotFoundException;
import com.paymentflow.payment.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions APIs", description = "Operations related to transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }



    @Operation(summary = "transfer money", description = "user can transfer money to another user")
    @PostMapping(value = "/transfer",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalApiResponse<TransferRequest>> transfterMoney(@Valid @RequestBody TransferRequest request) throws DataNotFoundException {
        return new ResponseEntity<>(transactionService.transferMoney(request),HttpStatus.OK);
    }

}

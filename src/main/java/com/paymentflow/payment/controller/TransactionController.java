package com.paymentflow.payment.controller;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.TransactionResponse;
import com.paymentflow.payment.dto.TransferRequest;
import com.paymentflow.payment.exception.DataNotFoundException;
import com.paymentflow.payment.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "transaction details",description = "Fetch all transaction details by transaction id")
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalApiResponse<TransactionResponse>> fetchTransactionById(@PathVariable Long id) throws DataNotFoundException {
        return new ResponseEntity<>(transactionService.fetchTransactionsById(id),HttpStatus.OK);
    }

    @Operation(summary = "All transaction List",description = "Return all Transaction list")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalApiResponse<List<TransactionResponse>>> getAllTrxnList(){
        return new ResponseEntity<>(transactionService.getAllTxn(),HttpStatus.OK);
    }


}

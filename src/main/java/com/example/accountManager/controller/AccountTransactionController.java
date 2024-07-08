package com.example.accountManager.controller;

import com.example.accountManager.entity.AccountTransaction;
import com.example.accountManager.service.AccountTransactionService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class AccountTransactionController {

    @Autowired
    private AccountTransactionService accountTransactionService;

    @Autowired
    private SqsService sqsService;

    @GetMapping
    public ResponseEntity<List<AccountTransaction>> getAllTransactions() {
        String requestId = UUID.randomUUID().toString();
        List<AccountTransaction> transactions = accountTransactionService.getAllTransactions();
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get All transactions", transactions);
        sqsService.sendMessage(message);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTransaction> getTransactionById(@PathVariable Long id) {
        String requestId = UUID.randomUUID().toString();
        AccountTransaction transaction = accountTransactionService.getTransactionById(id);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get transaction "+id, transaction);
        // Send message to SQS
        sqsService.sendMessage(message);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<AccountTransaction>> getTransactionsByAccountId(@PathVariable Long accountId) {
        String requestId = UUID.randomUUID().toString();
        List<AccountTransaction> transactions = accountTransactionService.getTransactionsByAccountId(accountId);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get transactions from account "+accountId, transactions);
        // Send message to SQS
        sqsService.sendMessage(message);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}

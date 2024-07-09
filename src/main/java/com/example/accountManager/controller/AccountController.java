package com.example.accountManager.controller;

import com.example.accountManager.service.AccountStatusService;
import com.example.accountManager.service.CustomerStatusService;
import com.example.accountManager.util.AmountRequest;
import com.example.accountManager.entity.Account;
import com.example.accountManager.service.AccountService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import com.example.accountManager.entity.*;
import com.example.accountManager.util.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SqsService sqsService;

    @Autowired
    private AccountStatusService accountStatusService;

    @Autowired
    private CustomerStatusService customerStatusService;


    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        String requestId = UUID.randomUUID().toString();
        Account createdAccount = accountService.createAccount(account);
        // Create message
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "create "+account.toString(), createdAccount.toString());

        // Send message to SQS
        sqsService.sendMessage(message);

        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id){
        BigDecimal num = accountService.getBalance(id);
        return new ResponseEntity<>(num, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        String requestId = UUID.randomUUID().toString();
        Account updatedAccount = accountService.updateAccount(id, account);
        // Create message
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "update "+account.toString(), updatedAccount.toString());
        // Send message to SQS
        sqsService.sendMessage(message);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        String requestId = UUID.randomUUID().toString();
        accountService.deleteAccount(id);
        // Create message
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "delete account "+id, "Deleted");
        // Send message to SQS
        sqsService.sendMessage(message);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long accountId, @RequestBody BigDecimal amount) {
        String requestId = UUID.randomUUID().toString();
        Account account = accountService.getAccountById(accountId);

        if (account == null) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
        // Get current balance
        BigDecimal currentBalance = accountService.getBalance(accountId);
        //System.out.println(account.getStatus().getStatusName().toString());
        if(!account.getStatus().getStatusName().toString().equals("OPEN")){
            return new ResponseEntity<>("account is not open", HttpStatus.BAD_REQUEST);
        }

        Customer customer = account.getCustomer();

        if(!customer.getStatus().getStatusName().toString().equals("ACTIVE")){
            return new ResponseEntity<>("Customer status is not active", HttpStatus.BAD_REQUEST);
        }

        // Check if withdrawal amount is greater than the current balance
        if (currentBalance.compareTo(amount) < 0) {
            return new ResponseEntity<>("Insufficient balance", HttpStatus.BAD_REQUEST);
        }





        // Proceed with the withdrawal
        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription("Withdrawal of amount: " + amount);
        accountService.saveTransaction(transaction);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "withdraw from account "+accountId, transaction.toString());
        sqsService.sendMessage(message);
        return new ResponseEntity<>(transaction.toString(), HttpStatus.OK);
    }


    @PostMapping("/{accountId}/topup")
    public ResponseEntity<AccountTransaction> topUp(@PathVariable Long accountId, @RequestBody BigDecimal amount) {
        String requestId = UUID.randomUUID().toString();
        Account account = accountService.getAccountById(accountId);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!account.getStatus().getStatusName().toString().equals("OPEN")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = account.getCustomer();

        if(!customer.getStatus().getStatusName().toString().equals("ACTIVE")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.TOPUP);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription("Top-up of amount: " + amount);
        accountService.saveTransaction(transaction);

        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "topup "+ accountId, transaction.toString());
        sqsService.sendMessage(message);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}

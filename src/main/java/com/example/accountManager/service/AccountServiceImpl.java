package com.example.accountManager.service;

import com.example.accountManager.entity.Account;
import com.example.accountManager.repository.AccountRepository;
import com.example.accountManager.service.AccountService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import com.example.accountManager.entity.*;
import com.example.accountManager.repository.*;
import com.example.accountManager.util.*;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private SqsService sqsService;

    @Override
    public Account createAccount(Account account) {
        Account createdAccount = accountRepository.save(account);
        return createdAccount;
    }
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Override
    public Account updateAccount(Long id, Account accountDetails) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            account.setAccountNumber(accountDetails.getAccountNumber());
            account.setCustomer(accountDetails.getCustomer());
            accountRepository.save(account);
            return account;
        }
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Account topUpAccount(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            accountRepository.save(account);
            return account;
        }
        return null;
    }

    @Override
    @Transactional
    public Account withdrawFromAccount(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            accountRepository.save(account);
            return account;
        }
        return null;
    }
    @Override
    public AccountTransaction saveTransaction(AccountTransaction transaction) {
        return accountTransactionRepository.save(transaction);
    }
    @Override
    public BigDecimal getBalance(Long accountId) {
        // Fetch all transactions for the given account ID
        List<AccountTransaction> transactions = accountTransactionRepository.findByAccountId(accountId);

        // Initialize sums for top-ups and withdrawals
        BigDecimal topUpSum = BigDecimal.ZERO;
        BigDecimal withdrawSum = BigDecimal.ZERO;

        // Calculate the sums for top-ups and withdrawals
        for (AccountTransaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionType.TOPUP) {
                topUpSum = topUpSum.add(transaction.getAmount());
            } else if (transaction.getTransactionType() == TransactionType.WITHDRAW) {
                withdrawSum = withdrawSum.add(transaction.getAmount());
            }
        }


        BigDecimal balance = topUpSum.subtract(withdrawSum);

        return balance;


    }

}

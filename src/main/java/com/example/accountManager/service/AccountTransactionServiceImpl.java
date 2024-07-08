package com.example.accountManager.service;

import com.example.accountManager.entity.AccountTransaction;
import com.example.accountManager.repository.AccountTransactionRepository;
import com.example.accountManager.service.AccountTransactionService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Autowired
    private AccountTransactionRepository transactionRepository;

    @Autowired
    private SqsService sqsService;

    @Override
    public AccountTransaction createTransaction(AccountTransaction transaction) {
        AccountTransaction createdTransaction = transactionRepository.save(transaction);
        return createdTransaction;
    }

    @Override
    public List<AccountTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public AccountTransaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AccountTransaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}

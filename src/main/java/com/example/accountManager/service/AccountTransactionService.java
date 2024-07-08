package com.example.accountManager.service;

import com.example.accountManager.entity.AccountTransaction;
import java.util.List;

public interface AccountTransactionService {
    AccountTransaction createTransaction(AccountTransaction transaction);
    List<AccountTransaction> getAllTransactions();
    AccountTransaction getTransactionById(Long id);
    List<AccountTransaction> getTransactionsByAccountId(Long accountId);
}

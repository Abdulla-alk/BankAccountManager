package com.example.accountManager.service;

import com.example.accountManager.entity.Account;
import java.math.BigDecimal;
import java.util.List;

import com.example.accountManager.entity.AccountTransaction;
import com.example.accountManager.util.*;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    List<Account> getAccountsByCustomerId(Long customerId);
    Account updateAccount(Long id, Account accountDetails);
    void deleteAccount(Long id);
    Account topUpAccount(Long accountId, BigDecimal amount);
    Account withdrawFromAccount(Long accountId, BigDecimal amount);
    AccountTransaction saveTransaction(AccountTransaction transaction);

    BigDecimal getBalance(Long id);

}

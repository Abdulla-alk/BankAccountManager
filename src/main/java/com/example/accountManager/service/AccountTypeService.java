package com.example.accountManager.service;

import com.example.accountManager.entity.AccountType;
import java.util.List;

public interface AccountTypeService {
    List<AccountType> getAllAccountTypes();
    AccountType getAccountTypeById(Long id);
}

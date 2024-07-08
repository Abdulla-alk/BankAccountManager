package com.example.accountManager.service;

import com.example.accountManager.entity.AccountType;
import com.example.accountManager.repository.AccountTypeRepository;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeRepository typeRepository;
    @Autowired
    private SqsService sqsService;

    public AccountType createAccountType(AccountType type) {
        AccountType createdType = typeRepository.save(type);
        return createdType;
    }

    public List<AccountType> getAllAccountTypes() {
        return typeRepository.findAll();
    }
    public AccountType getAccountTypeById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }
}

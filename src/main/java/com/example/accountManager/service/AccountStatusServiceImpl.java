package com.example.accountManager.service;

import com.example.accountManager.entity.AccountStatus;
import com.example.accountManager.repository.AccountStatusRepository;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountStatusServiceImpl implements AccountStatusService{

    @Autowired
    private AccountStatusRepository statusRepository;

    @Autowired
    private SqsService sqsService;

    public AccountStatus createAccountStatus(AccountStatus status) {
        AccountStatus createdStatus = statusRepository.save(status);
        return createdStatus;
    }

    public List<AccountStatus> getAllAccountStatuses() {
        return statusRepository.findAll();
    }

    public AccountStatus getAccountStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }
}

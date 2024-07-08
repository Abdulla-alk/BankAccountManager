package com.example.accountManager.service;

import com.example.accountManager.entity.AccountStatus;
import java.util.List;

public interface AccountStatusService {
    List<AccountStatus> getAllAccountStatuses();
    AccountStatus getAccountStatusById(Long id);
}

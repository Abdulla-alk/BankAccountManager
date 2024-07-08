package com.example.accountManager.service;

import com.example.accountManager.entity.CustomerStatus;
import java.util.List;

public interface CustomerStatusService {
    List<CustomerStatus> getAllCustomerStatuses();
    CustomerStatus getCustomerStatusById(Long id);
}

package com.example.accountManager.service;

import com.example.accountManager.entity.CustomerStatus;
import com.example.accountManager.repository.CustomerStatusRepository;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerStatusServiceImpl implements CustomerStatusService{

    @Autowired
    private CustomerStatusRepository statusRepository;

    @Autowired
    private SqsService sqsService;

    public CustomerStatus createCustomerStatus(CustomerStatus status) {
        CustomerStatus createdStatus = statusRepository.save(status);
        return createdStatus;

    }

    public List<CustomerStatus> getAllCustomerStatuses() {
        return statusRepository.findAll();
    }

    public CustomerStatus getCustomerStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }
}

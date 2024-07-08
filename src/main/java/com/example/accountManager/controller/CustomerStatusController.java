package com.example.accountManager.controller;

import com.example.accountManager.entity.CustomerStatus;
import com.example.accountManager.service.CustomerStatusService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customerStatuses")
public class CustomerStatusController {

    @Autowired
    private CustomerStatusService statusService;

    @Autowired
    private SqsService sqsService;
    @GetMapping
    public ResponseEntity<List<CustomerStatus>> getAllCustomerStatuses() {
        String requestId = UUID.randomUUID().toString();
        List<CustomerStatus> statuses = statusService.getAllCustomerStatuses();
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get all statuses ", statuses);
        // Send message to SQS
        sqsService.sendMessage(message);
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerStatus> getCustomerStatusById(@PathVariable Long id) {
        String requestId = UUID.randomUUID().toString();
        CustomerStatus status = statusService.getCustomerStatusById(id);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get status "+id, status);

        // Send message to SQS
        sqsService.sendMessage(message);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }
}

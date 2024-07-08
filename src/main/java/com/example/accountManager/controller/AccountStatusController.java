package com.example.accountManager.controller;

import com.example.accountManager.entity.AccountStatus;
import com.example.accountManager.service.AccountStatusService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accountStatuses")
public class AccountStatusController {

    @Autowired
    private AccountStatusService statusService;

    @Autowired
    private SqsService sqsService;

    @GetMapping
    public ResponseEntity<List<AccountStatus>> getAllAccountStatuses() {
        String requestId = UUID.randomUUID().toString();
        List<AccountStatus> statuses = statusService.getAllAccountStatuses();
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "getAllAccountStatuses", statuses);
        // Send message to SQS
        sqsService.sendMessage(message);
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountStatus> getAccountStatusById(@PathVariable Long id) {
        String requestId = UUID.randomUUID().toString();
        AccountStatus status = statusService.getAccountStatusById(id);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get account status "+id, status);
        // Send message to SQS
        sqsService.sendMessage(message);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }
}

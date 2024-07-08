package com.example.accountManager.controller;

import com.example.accountManager.entity.AccountType;
import com.example.accountManager.service.AccountTypeService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accountTypes")
public class AccountTypeController {

    @Autowired
    private AccountTypeService typeService;

    @Autowired
    private SqsService sqsService;

    @GetMapping
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        String requestId = UUID.randomUUID().toString();

        List<AccountType> types = typeService.getAllAccountTypes();
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get All account types", types);
        sqsService.sendMessage(message);
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountType> getAccountTypeById(@PathVariable Long id) {
        String requestId = UUID.randomUUID().toString();
        AccountType type = typeService.getAccountTypeById(id);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get account type "+id, type);
        // Send message to SQS
        sqsService.sendMessage(message);
        return type != null ? ResponseEntity.ok(type) : ResponseEntity.notFound().build();
    }
}

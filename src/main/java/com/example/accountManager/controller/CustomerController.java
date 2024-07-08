package com.example.accountManager.controller;

import com.example.accountManager.entity.Customer;
import com.example.accountManager.service.CustomerService;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SqsService sqsService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        String requestId = UUID.randomUUID().toString();
        Customer createdCustomer = customerService.createCustomer(customer);

        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "Create "+customer.toString(), createdCustomer.toString());

        sqsService.sendMessage(message);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        String requestId = UUID.randomUUID().toString();
        List<Customer> customers = customerService.getAllCustomers();
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get all customers ", customers);

        sqsService.sendMessage(message);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        String requestId = UUID.randomUUID().toString();
        Customer customer = customerService.getCustomerById(id);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "get customer "+id, customer.toString());
        sqsService.sendMessage(message);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        String requestId = UUID.randomUUID().toString();
        Customer updatedCustomer = customerService.updateCustomer(id, customer);

        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "update customer "+customer.toString(), updatedCustomer.toString());
        sqsService.sendMessage(message);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        String requestId = UUID.randomUUID().toString();
        customerService.deleteCustomer(id);
        String message = String.format("{\"requestId\": \"%s\", \"request\": \"%s\", \"response\": \"%s\"}",
                requestId, "Delete customer "+id, "Deleted");
        sqsService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

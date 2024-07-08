package com.example.accountManager.service;

import com.example.accountManager.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, Customer customerDetails);
    void deleteCustomer(Long id);

}

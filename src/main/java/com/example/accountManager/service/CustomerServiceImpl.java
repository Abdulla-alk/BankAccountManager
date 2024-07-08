package com.example.accountManager.service;

import com.example.accountManager.entity.Customer;
import com.example.accountManager.repository.AccountTransactionRepository;
import com.example.accountManager.repository.CustomerRepository;
import com.example.accountManager.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private SqsService sqsService;

    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhone(customerDetails.getPhone());
            customer.setAddress(customerDetails.getAddress());
            customer.setIdCardNumber(customerDetails.getIdCardNumber());
            customer.setDateOfBirth(customerDetails.getDateOfBirth());
            customerRepository.save(customer);
            return customer;
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}

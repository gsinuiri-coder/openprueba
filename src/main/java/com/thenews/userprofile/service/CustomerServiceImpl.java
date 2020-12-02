package com.thenews.userprofile.service;


import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.userprofile.domain.model.Customer;
import com.thenews.userprofile.domain.repository.CustomerRepository;
import com.thenews.userprofile.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "Id", customerId));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerRequest) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "Id", customerId));
        return customerRepository.save(
                customer.setTypeFeeding(customerRequest.getTypeFeeding()));
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "Id", customerId));
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }
}

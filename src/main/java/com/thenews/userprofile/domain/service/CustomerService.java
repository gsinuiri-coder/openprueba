package com.thenews.userprofile.domain.service;

import com.thenews.userprofile.domain.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customerRequest);
    ResponseEntity<?> deleteCustomer(Long customerId);
}

package com.app.store.controller;

import com.app.store.entities.Customer;
import com.app.store.exceptions.CustomerNotFoundException;
import com.app.store.exceptions.ExceptionResponse;
import com.app.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customer")
    public void saveCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    @DeleteMapping("/customer/delete/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(new ExceptionResponse("No customer with ID: " + customerId + " found", "13", new Date()));
        }
        customerRepository.delete(customer.get());
    }

    @PutMapping("/customer/update")
    public void updateCustomer(@RequestBody Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(customer.getCustomerId());
        if (!customerToUpdate.isPresent()) {
            throw new CustomerNotFoundException(new ExceptionResponse("No customer with ID: " + customer.getCustomerId() + " found", "13", new Date()));
        }
        customerRepository.save(customer);
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(new ExceptionResponse("No customer with ID: " + customerId + " found", "13", new Date()));
        }
        return customerRepository.findById(customerId).get();
    }
}

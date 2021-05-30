package com.app.store.controller;

import com.app.store.entities.Customer;
import com.app.store.infra.Utils;
import com.app.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            throw Utils.getInstance().getCustomerNotFoundException(customerId);
        }
        customerRepository.delete(customer.get());
    }

    @PutMapping("/customer/update")
    public void updateCustomer(@RequestBody Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(customer.getCustomerId());
        if (!customerToUpdate.isPresent()) {
            throw Utils.getInstance().getCustomerNotFoundException(customer.getCustomerId());
        }
        customerRepository.save(customer);
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw Utils.getInstance().getCustomerNotFoundException(customerId);
        }
        return customerRepository.findById(customerId).get();
    }


}

package com.app.store.controller;

import com.app.store.entities.Customer;
import com.app.store.entities.OrderDetails;
import com.app.store.entities.Product;
import com.app.store.entities.ProductOrders;
import com.app.store.exceptions.CustomerNotFoundException;
import com.app.store.exceptions.ExceptionResponse;
import com.app.store.exceptions.ProductNotFoundException;
import com.app.store.repository.CustomerRepository;
import com.app.store.repository.OrderRepository;
import com.app.store.repository.ProductOrdersRepository;
import com.app.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class OrderController {

    private final ProductOrdersRepository productRepository;
    private final ProductRepository productRepo;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderController(ProductOrdersRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepo) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepo = productRepo;
    }

    @PostMapping("order/{orderID}/customer/{customerID}/product/{productID}")
    public void saveOrderForCustomer(@PathVariable("orderID") long orderID, @PathVariable("customerID") long customerID, @PathVariable("productID") long productID) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(new ExceptionResponse("No customer with ID: " + customerID + " found", "13", new Date()));
        }
        Optional<Product> productFound = productRepo.findById(productID);
        if (!productFound.isPresent()) {
            throw new ProductNotFoundException(new ExceptionResponse("No product with ID: " + productID + " found", "13", new Date()));
        }
        ProductOrders productOrders = new ProductOrders();
        productOrders.setProductID(productID);

        Optional<OrderDetails> order = orderRepository.findById(orderID);
        if (!order.isPresent()) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setCustomerID(customerID);
            OrderDetails createdOrder = orderRepository.save(orderDetails);
            productOrders.setOrderDetails(createdOrder);
            productRepository.save(productOrders);
        } else {
            productOrders.setOrderDetails(order.get());
            productRepository.save(productOrders);
        }
    }
}

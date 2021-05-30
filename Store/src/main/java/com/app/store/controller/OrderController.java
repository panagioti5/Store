package com.app.store.controller;

import com.app.store.entities.Customer;
import com.app.store.entities.CustomerOrders;
import com.app.store.entities.Product;
import com.app.store.entities.ProductOrders;
import com.app.store.infra.Utils;
import com.app.store.repository.CustomerRepository;
import com.app.store.repository.OrderRepository;
import com.app.store.repository.ProductOrdersRepository;
import com.app.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw Utils.getInstance().getCustomerNotFoundException(customerID);
        }
        Optional<Product> productFound = productRepo.findById(productID);
        if (!productFound.isPresent()) {
            throw Utils.getInstance().getProductNotFoundException(productID);
        }
        ProductOrders productOrders = new ProductOrders();
        productOrders.setProductID(productID);

        Optional<CustomerOrders> order = orderRepository.findById(orderID);
        if (!order.isPresent()) {
            CustomerOrders customerOrders = new CustomerOrders();
            customerOrders.setCustomerID(customerID);
            CustomerOrders createdOrder = orderRepository.save(customerOrders);
            productOrders.setCustomerOrders(createdOrder);
        } else {
            productOrders.setCustomerOrders(order.get());
        }
        productRepository.save(productOrders);
    }

    @GetMapping("orders/customer/{customerID}")
    public List<CustomerOrders> getOrdersByCustomerID(@PathVariable("customerID") long customerID){
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(!customer.isPresent()){
            throw Utils.getInstance().getCustomerNotFoundException(customerID);
        }
        return orderRepository.findByCustomerID(customerID);
    }

    @GetMapping("products/customer/{customerID}")
    public List<ProductOrders> getProductsOrdersByCustomerID(@PathVariable("customerID") long customerID){
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(!customer.isPresent()){
            throw Utils.getInstance().getCustomerNotFoundException(customerID);
        }
        List<CustomerOrders> customerOrders = orderRepository.findByCustomerID(customerID);
        return customerOrders.stream().map(productRepository::findByCustomerOrders).flatMap(List::stream).collect(Collectors.toList());
    }
}

package com.app.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class CustomerOrders {

    @Id
    @Column
    @GeneratedValue
    private Long orderId;

    @Column
    private Long customerID;

    @OneToMany(mappedBy = "customerOrders")
    @JsonIgnore
    private List<ProductOrders> customerProducts;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public List<ProductOrders> getCustomerProducts() {
        return customerProducts;
    }

    public void setCustomerProducts(List<ProductOrders> customerProducts) {
        this.customerProducts = customerProducts;
    }

}

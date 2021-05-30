package com.app.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class ProductOrders {

    @Id
    @JsonIgnore
    @GeneratedValue
    private Long entryID;

    @Column
    private Long productID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "ORDER_ID")
    private CustomerOrders customerOrders;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public CustomerOrders getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(CustomerOrders customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Long getEntryID() {
        return entryID;
    }

    public void setEntryID(Long entryID) {
        this.entryID = entryID;
    }

}

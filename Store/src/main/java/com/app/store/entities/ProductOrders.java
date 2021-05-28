package com.app.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class ProductOrders {

    @Id
    @GeneratedValue
    private Long entryID;

    @Column
    private Long productID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private OrderDetails orderDetails;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }


    public Long getEntryID() {
        return entryID;
    }

    public void setEntryID(Long entryID) {
        this.entryID = entryID;
    }
}

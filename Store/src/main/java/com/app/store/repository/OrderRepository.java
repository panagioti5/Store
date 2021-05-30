package com.app.store.repository;

import com.app.store.entities.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrders, Long> {
    List<CustomerOrders> findByCustomerID(Long customerID);

}

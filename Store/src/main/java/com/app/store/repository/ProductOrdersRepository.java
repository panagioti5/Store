package com.app.store.repository;

import com.app.store.entities.CustomerOrders;
import com.app.store.entities.ProductOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrdersRepository extends JpaRepository<ProductOrders, Long> {
    List<ProductOrders> findByCustomerOrders(CustomerOrders customerOrders);
}

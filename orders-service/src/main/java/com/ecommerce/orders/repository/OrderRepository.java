package com.ecommerce.orders.repository;

import com.ecommerce.orders.entity.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Order entity to perform CRUD operations.
 */
@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    // Additional custom query methods can be defined here if needed
}

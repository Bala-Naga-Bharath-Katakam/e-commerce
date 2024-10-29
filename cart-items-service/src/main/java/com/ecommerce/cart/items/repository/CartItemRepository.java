package com.ecommerce.cart.items.repository;

import com.ecommerce.cart.items.entity.CartItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing cart items.
 */
@Repository
public interface CartItemRepository extends ReactiveCrudRepository<CartItem, Long> {
    // You can define custom query methods here if needed
}


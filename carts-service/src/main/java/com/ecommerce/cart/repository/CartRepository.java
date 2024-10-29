package com.ecommerce.cart.repository;

import com.ecommerce.cart.entity.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Cart entities.
 */
@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, Long> {
    // Additional query methods can be defined here
}

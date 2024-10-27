package com.ecommerce.carts.repository;

import com.ecommerce.carts.entity.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, Long> {
}


package com.ecommerce.products.repository;

import com.ecommerce.products.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Product entities.
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}

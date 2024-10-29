package com.ecommerce.products.repository;

import com.ecommerce.products.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository interface for Product entity operations.
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    /**
     * Finds products by category ID, ordered by price in ascending order.
     *
     * @param categoryId the ID of the category
     * @return a Flux containing the products in the specified category, ordered by price
     */
    @Query("SELECT * FROM products WHERE category_id = :categoryId ORDER BY price ASC")
    Flux<Product> findByCategoryIdOrderByPriceAsc(Long categoryId);

    /**
     * Finds products by a keyword in the product name, ignoring case.
     *
     * @param keyword the keyword to search for in the product name
     * @param sort    the sorting order
     * @return a Flux containing products with names matching the keyword
     */
    Flux<Product> findByProductNameContainingIgnoreCase(String keyword, Sort sort);
}


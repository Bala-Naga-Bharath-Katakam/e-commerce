package com.ecommerce.products.repository;

import com.ecommerce.products.entity.Product;
import org.springframework.data.domain.Pageable;
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
     * Finds products by their name using a case-insensitive search.
     *
     * @param productName the name of the product to search for
     * @param pageable    pagination details
     * @return a Flux of products matching the search criteria
     */
    Flux<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);

}


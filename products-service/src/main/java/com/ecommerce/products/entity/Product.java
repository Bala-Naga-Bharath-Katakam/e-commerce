package com.ecommerce.products.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a product in the eCommerce system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("products")
public class Product {

    /** Unique identifier for the product. */
    @Id
    private Long productId;

    /** Name of the product. */
    @Column("product_name")
    private String productName;

    /** Description of the product. */
    @Column("description")
    private String description;

    /** Price of the product. */
    @Column("price")
    private Double price;

    /** ID of the category this product belongs to. */
    @Column("category_id")
    private Long categoryId;
}

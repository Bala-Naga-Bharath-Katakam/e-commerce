package com.ecommerce.products.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entity representing a product in the eCommerce application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("products")
public class Product {

    @Id
    @Column("product_id")
    private Long productId;

    @NotBlank
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    private String productName;

    private String image;

    @NotBlank
    @Size(min = 6, message = "Product description must contain at least 6 characters")
    private String description;

    private Integer quantity;

    private double price;

    private double discount;

    private double specialPrice;

    // categoryId and userId serve as foreign keys but are not direct relationships in R2DBC
    @Column("category_id")
    private Long categoryId;

    @Column("seller_id")
    private Long sellerId;
}

package com.ecommerce.cart.items.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents an item in the shopping cart.
 */
@Table("cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    private Long cartItemId; // Unique identifier for the cart item

    private Long cartId; // Reference to the associated cart

    private Long productId; // Reference to the associated product

    private Integer quantity; // Quantity of the product

    private double discount; // Discount applied to the product

    private double productPrice; // Price of the product at the time of adding to the cart
}


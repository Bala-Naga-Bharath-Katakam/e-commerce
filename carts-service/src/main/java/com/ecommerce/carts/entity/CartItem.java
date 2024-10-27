package com.ecommerce.carts.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItem {
    @Id
    private Long id;
    private Long productId; // Foreign Key
    private int quantity;

    // Getters and Setters
}

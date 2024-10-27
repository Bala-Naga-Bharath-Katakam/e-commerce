package com.ecommerce.orders.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItem {
    @Id
    private Long id;
    private Long productId; // Foreign Key
    private int quantity;

    // Getters and Setters
}

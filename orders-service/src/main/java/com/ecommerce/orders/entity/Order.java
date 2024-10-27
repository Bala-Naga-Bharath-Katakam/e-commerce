package com.ecommerce.orders.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    private Long id;
    private Long userId; // Foreign Key
    private List<OrderItem> items;

    // Getters and Setters
}


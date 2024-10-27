package com.ecommerce.carts.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    private Long id;
    private Long userId; // Foreign Key

    // Assuming you have a CartItem class
    private List<CartItem> items;

    // Getters and Setters
}


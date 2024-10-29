package com.ecommerce.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;

/**
 * Represents a shopping cart in the e-commerce application.
 */
@Table("carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private Long cartId;

    private Long userId; // Store user ID instead of a User entity for decoupling

}

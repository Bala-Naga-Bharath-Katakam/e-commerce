package com.ecommerce.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.time.LocalDate;
import java.util.List;

/**
 * Represents an order in the e-commerce application.
 */
@Table("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private Long orderId;

    private String email; // Email of the user placing the order

    private List<Long> cartItemIds; // List of CartItem IDs included in the order

    private LocalDate orderDate; // Date of the order

    private Long paymentId; // Reference to payment ID

    private Double totalAmount; // Total amount for the order

    private String orderStatus; // Status of the order (e.g., PENDING, COMPLETED, CANCELED)

    private Long addressId; // Reference to address ID
}

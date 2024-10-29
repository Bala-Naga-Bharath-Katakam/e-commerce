package com.ecommerce.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a payment in the e-commerce application.
 */
@Table("payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    private Long paymentId; // Unique identifier for the payment

    private Long orderId; // Identifier of the associated order

    private Double amount; // Amount to be paid

    private String status; // Payment status (e.g., PENDING, COMPLETED, FAILED)
}

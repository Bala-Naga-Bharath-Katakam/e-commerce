package com.ecommerce.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

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
    private Long userId; // Identifier of the user making the payment
    private Double amount; // Amount to be paid

    private String paymentMethod; // Method used for payment (e.g., "CREDIT_CARD", "WALLET")
    private String status; // Payment status (e.g., "PENDING", "COMPLETED", "FAILED", "REFUNDED")
    private LocalDateTime paymentDate; // Date and time of the payment
    private LocalDateTime updatedDate; // Date and time of the last update

    private String transactionId; // Unique identifier from the payment gateway
    private Boolean isRefundable; // Indicates if the payment is eligible for refund
}

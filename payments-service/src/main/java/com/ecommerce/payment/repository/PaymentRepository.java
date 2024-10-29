package com.ecommerce.payment.repository;


import com.ecommerce.payment.entity.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing payment data.
 */
@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {
    // Additional query methods can be defined here if needed
}

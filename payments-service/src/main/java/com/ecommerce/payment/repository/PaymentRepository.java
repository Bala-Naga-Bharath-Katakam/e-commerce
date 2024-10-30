package com.ecommerce.payment.repository;

import com.ecommerce.payment.entity.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository for performing CRUD operations on Payment entities.
 */
@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {

    /**
     * Retrieves all payments associated with a specific user ID.
     *
     * @param userId the ID of the user.
     * @return a Flux of Payment entities associated with the specified user ID.
     */
    Flux<Payment> findByUserId(Long userId);

    /**
     * Retrieves all payment methods associated with a specific user ID if payment methods are stored.
     *
     * @param userId the ID of the user.
     * @return a Flux of Strings representing payment methods.
     */
    Flux<String> findPaymentMethodsByUserId(Long userId);
}

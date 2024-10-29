package com.ecommerce.payment.service;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class for handling payment operations.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * Creates a new payment.
     *
     * @param payment the payment to create
     * @return a ResponseEntity containing the created payment
     */
    public Mono<ResponseEntity<Payment>> createPayment(Payment payment) {
        return paymentRepository.save(payment)
                .map(savedPayment -> ResponseEntity.ok(savedPayment))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    /**
     * Retrieves a payment by its ID.
     *
     * @param paymentId the ID of the payment to retrieve
     * @return a ResponseEntity containing the payment if found, or not found
     */
    public Mono<ResponseEntity<Payment>> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> ResponseEntity.ok(payment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all payments.
     *
     * @return a Flux of ResponseEntity containing payments
     */
    public Flux<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    /**
     * Deletes a payment by its ID.
     *
     * @param paymentId the ID of the payment to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    public Mono<ResponseEntity<Object>> deletePayment(Long paymentId) {
        return paymentRepository.deleteById(paymentId)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorReturn(ResponseEntity.notFound().build());
    }
}

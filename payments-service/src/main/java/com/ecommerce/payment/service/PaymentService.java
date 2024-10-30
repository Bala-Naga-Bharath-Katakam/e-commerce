package com.ecommerce.payment.service;

import com.ecommerce.payment.entity.Payment;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing payments.
 */
public interface PaymentService {

    Mono<ResponseEntity<Payment>> initiatePaymentViaGateway(Payment payment);

    Mono<ResponseEntity<Payment>> createPayment(Payment payment);

    Mono<ResponseEntity<Payment>> getPaymentById(Long paymentId);

    Flux<ResponseEntity<Payment>> getPaymentsByUserId(Long userId);

    Mono<ResponseEntity<Payment>> cancelPayment(Long paymentId);

    Mono<ResponseEntity<Payment>> refundPayment(Long paymentId);

    Mono<ResponseEntity<Payment>> updatePaymentStatus(Long paymentId, String status);

    Mono<ResponseEntity<Void>> addPaymentMethod(Long userId, String paymentMethod);

    Flux<String> getUserPaymentMethods(Long userId);
}

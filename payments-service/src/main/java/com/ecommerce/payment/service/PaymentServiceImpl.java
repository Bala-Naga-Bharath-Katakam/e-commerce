package com.ecommerce.payment.service;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of the PaymentService interface for managing payments.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGatewayService paymentGatewayService;


    /**
     * Constructor for PaymentServiceImpl.
     *
     * @param paymentRepository the payment repository for CRUD operations.
     */
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentGatewayService paymentGatewayService) {
        this.paymentRepository = paymentRepository;
        this.paymentGatewayService = paymentGatewayService;
    }

    @Override
    public Mono<ResponseEntity<Payment>> initiatePaymentViaGateway(Payment payment) {
        // Initiate the payment through the payment gateway
        return paymentGatewayService.initiatePayment(payment.getAmount(), "usd", payment.getPaymentMethod(), "Payment for Order ID: " + payment.getOrderId())
                .flatMap(transactionId -> {
                    payment.setTransactionId(transactionId); // Set the transaction ID
                    return paymentRepository.save(payment) // Save the payment to the database
                            .map(ResponseEntity::ok);
                });
    }

    /**
     * Creates a new payment.
     *
     * @param payment the Payment object to be created.
     * @return a Mono containing ResponseEntity with the created Payment.
     */
    @Override
    public Mono<ResponseEntity<Payment>> createPayment(Payment payment) {
        return paymentRepository.save(payment)
                .map(ResponseEntity::ok);
    }

    /**
     * Retrieves a payment by its ID.
     *
     * @param paymentId the ID of the payment to retrieve.
     * @return a Mono containing ResponseEntity with the Payment if found, or 404 Not Found if not.
     */
    @Override
    public Mono<ResponseEntity<Payment>> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all payments associated with a specific user ID.
     *
     * @param userId the ID of the user whose payments are to be retrieved.
     * @return a Flux of ResponseEntity containing Payments for the specified user.
     */
    @Override
    public Flux<ResponseEntity<Payment>> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId)
                .map(ResponseEntity::ok);
    }

    /**
     * Cancels a payment by updating its status to "CANCELLED".
     *
     * @param paymentId the ID of the payment to cancel.
     * @return a Mono containing ResponseEntity with the updated Payment if found, or 404 Not Found if not.
     */
    @Override
    public Mono<ResponseEntity<Payment>> cancelPayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .flatMap(payment -> {
                    payment.setStatus("CANCELLED");
                    return paymentRepository.save(payment);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Processes a refund for a payment, if it is eligible for a refund.
     *
     * @param paymentId the ID of the payment to be refunded.
     * @return a Mono containing ResponseEntity with the refunded Payment if eligible and found,
     *         or an error if not eligible, or 404 Not Found if not found.
     */
    @Override
    public Mono<ResponseEntity<Payment>> refundPayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .flatMap(payment -> {
                    if (Boolean.TRUE.equals(payment.getIsRefundable())) {
                        payment.setStatus("REFUNDED");
                        return paymentRepository.save(payment);
                    } else {
                        return Mono.error(new RuntimeException("Payment not eligible for refund"));
                    }
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Updates the status of an existing payment.
     *
     * @param paymentId the ID of the payment to update.
     * @param status the new status for the payment.
     * @return a Mono containing ResponseEntity with the updated Payment if found, or 404 Not Found if not.
     */
    @Override
    public Mono<ResponseEntity<Payment>> updatePaymentStatus(Long paymentId, String status) {
        return paymentRepository.findById(paymentId)
                .flatMap(payment -> {
                    payment.setStatus(status);
                    return paymentRepository.save(payment);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Adds a new payment method for a user.
     *
     * @param userId the ID of the user.
     * @param paymentMethod the new payment method to add.
     * @return a Mono containing ResponseEntity indicating the addition of the payment method.
     */
    @Override
    public Mono<ResponseEntity<Void>> addPaymentMethod(Long userId, String paymentMethod) {
        // Implementation of adding a new payment method would go here.
        return Mono.just(ResponseEntity.ok().build());
    }

    /**
     * Retrieves all payment methods associated with a user.
     *
     * @param userId the ID of the user.
     * @return a Flux containing the list of payment methods for the specified user.
     */
    @Override
    public Flux<String> getUserPaymentMethods(Long userId) {
        // Implementation of retrieving payment methods would go here.
        return Flux.just("CREDIT_CARD", "PAYPAL");
    }
}

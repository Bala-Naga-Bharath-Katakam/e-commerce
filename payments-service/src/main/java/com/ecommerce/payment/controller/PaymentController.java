package com.ecommerce.payment.controller;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing payments in the e-commerce application.
 */
@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment Service", description = "Manage payments in the e-commerce application")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Endpoint to create a new payment for an order.
     *
     * @param payment The payment information to be created.
     * @return A Mono containing the ResponseEntity with the created Payment.
     */
    @Operation(summary = "Create a new payment for an order")
    @PostMapping("/create")
    public Mono<ResponseEntity<Payment>> createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    /**
     * Endpoint to retrieve a payment by its ID.
     *
     * @param paymentId The ID of the payment.
     * @return A Mono containing the ResponseEntity with the requested Payment.
     */
    @Operation(summary = "Get payment by ID")
    @GetMapping("/{paymentId}")
    public Mono<ResponseEntity<Payment>> getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    /**
     * Endpoint to get all payments made by a specific user.
     *
     * @param userId The ID of the user.
     * @return A Flux of payments made by the specified user.
     */
    @Operation(summary = "Get all payments for a specific user")
    @GetMapping("/user/{userId}")
    public Flux<ResponseEntity<Payment>> getPaymentsByUserId(@PathVariable Long userId) {
        return paymentService.getPaymentsByUserId(userId);
    }

    /**
     * Endpoint to cancel a payment by its ID.
     *
     * @param paymentId The ID of the payment to be canceled.
     * @return A Mono containing the ResponseEntity with the updated Payment.
     */
    @Operation(summary = "Cancel a payment by ID")
    @PutMapping("/cancel/{paymentId}")
    public Mono<ResponseEntity<Payment>> cancelPayment(@PathVariable Long paymentId) {
        return paymentService.cancelPayment(paymentId);
    }

    /**
     * Endpoint to refund a payment by its ID.
     *
     * @param paymentId The ID of the payment to be refunded.
     * @return A Mono containing the ResponseEntity with the updated Payment.
     */
    @Operation(summary = "Refund a payment by ID")
    @PutMapping("/refund/{paymentId}")
    public Mono<ResponseEntity<Payment>> refundPayment(@PathVariable Long paymentId) {
        return paymentService.refundPayment(paymentId);
    }

    /**
     * Endpoint to update the status of a payment by its ID.
     *
     * @param paymentId The ID of the payment.
     * @param status    The new status for the payment.
     * @return A Mono containing the ResponseEntity with the updated Payment.
     */
    @Operation(summary = "Update payment status by ID")
    @PatchMapping("/{paymentId}/status")
    public Mono<ResponseEntity<Payment>> updatePaymentStatus(@PathVariable Long paymentId, @RequestParam String status) {
        return paymentService.updatePaymentStatus(paymentId, status);
    }

    /**
     * Endpoint to add a new payment method for the user.
     *
     * @param userId        The ID of the user.
     * @param paymentMethod The payment method to be added.
     * @return A Mono containing the ResponseEntity indicating the operation's success.
     */
    @Operation(summary = "Add a payment method for the user")
    @PostMapping("/user/{userId}/add-method")
    public Mono<ResponseEntity<Void>> addPaymentMethod(@PathVariable Long userId, @RequestParam String paymentMethod) {
        return paymentService.addPaymentMethod(userId, paymentMethod);
    }

    /**
     * Endpoint to retrieve all payment methods for a specific user.
     *
     * @param userId The ID of the user.
     * @return A Flux of payment methods associated with the specified user.
     */
    @Operation(summary = "List all payment methods for the user")
    @GetMapping("/user/{userId}/methods")
    public Flux<ResponseEntity<String>> getUserPaymentMethods(@PathVariable Long userId) {
        return paymentService.getUserPaymentMethods(userId)
                .map(ResponseEntity::ok);
    }
}

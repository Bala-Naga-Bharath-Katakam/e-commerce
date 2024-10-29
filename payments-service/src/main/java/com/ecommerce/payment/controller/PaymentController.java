package com.ecommerce.payment.controller;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST controller for managing payments.
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Creates a new payment.
     *
     * @param payment the payment to create
     * @return a ResponseEntity containing the created payment
     */
    @PostMapping
    @Operation(summary = "Create a new payment", description = "Creates a new payment in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payment request")
    })
    public Mono<ResponseEntity<Payment>> createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    /**
     * Retrieves a payment by its ID.
     *
     * @param paymentId the ID of the payment to retrieve
     * @return a ResponseEntity containing the payment if found, or not found
     */
    @GetMapping("/{paymentId}")
    @Operation(summary = "Get payment by ID", description = "Retrieves a payment using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public Mono<ResponseEntity<Payment>> getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    /**
     * Retrieves all payments.
     *
     * @return a Flux of payments
     */
    @GetMapping
    @Operation(summary = "Get all payments", description = "Retrieves a list of all payments in the system.")
    public Flux<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    /**
     * Deletes a payment by its ID.
     *
     * @param paymentId the ID of the payment to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{paymentId}")
    @Operation(summary = "Delete payment", description = "Deletes a payment using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public Mono<ResponseEntity<Object>> deletePayment(@PathVariable Long paymentId) {
        return paymentService.deletePayment(paymentId);
    }
}

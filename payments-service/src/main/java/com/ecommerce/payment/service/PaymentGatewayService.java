package com.ecommerce.payment.service;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service for handling payment gateway operations.
 */
@Service
public class PaymentGatewayService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public PaymentGatewayService() {
        Stripe.apiKey = this.secretKey; // Initialize Stripe with secret key
    }

    /**
     * Initiates a payment using Stripe.
     *
     * @param amount      The amount to charge.
     * @param currency    The currency for the charge.
     * @param source      The payment source (e.g., card token).
     * @param description Description of the charge.
     * @return Mono<String> containing the transaction ID.
     */
    public Mono<String> initiatePayment(Double amount, String currency, String source, String description) {
        return Mono.fromCallable(() -> {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount((long) (amount * 100)) // Convert to cents
                    .setCurrency(currency)
                    .setSource(source)
                    .setDescription(description)
                    .build();

            Charge charge = Charge.create(params);
            return charge.getId(); // Return the transaction ID
        });
    }
}

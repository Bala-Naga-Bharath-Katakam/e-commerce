package com.ecommerce.payment.quarkus.repository;

import com.ecommerce.payment.quarkus.entity.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing Payment entities.
 */
@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
    // No additional methods required; Panache provides basic CRUD
}


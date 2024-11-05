package com.ecommerce.payment.quarkus.controller;


import com.ecommerce.payment.quarkus.entity.Payment;
import com.ecommerce.payment.quarkus.repository.PaymentRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

/**
 * REST endpoint for managing payments.
 */
@Path("/payments")
@Tag(name = "Payment API", description = "Payment operations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentRepository paymentRepository;

    @GET
    @Operation(summary = "List all payments")
    public List<Payment> listAllPayments() {
        return paymentRepository.listAll();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get payment by ID")
    public jakarta.ws.rs.core.Response getPayment(@PathParam("id") Long id) {
        Payment payment = paymentRepository.findById(id);
        if (payment == null) {
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
        return jakarta.ws.rs.core.Response.ok(payment).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(Payment payment) {
        // Here you would save the payment using your repository
        // For demonstration purposes, we will just return the payment object
        paymentRepository.persist(payment);
        // Example response
        return Response.status(Response.Status.CREATED).entity(payment).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Update an existing payment")
    public jakarta.ws.rs.core.Response updatePayment(@PathParam("id") Long id, Payment updatedPayment) {
        Payment payment = paymentRepository.findById(id);
        if (payment == null) {
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
        payment.setAmount(updatedPayment.getAmount());
        payment.setStatus(updatedPayment.getStatus());
        paymentRepository.persist(payment);
        return jakarta.ws.rs.core.Response.ok(payment).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a payment")
    public jakarta.ws.rs.core.Response deletePayment(@PathParam("id") Long id) {
        boolean deleted = paymentRepository.deleteById(id);
        if (!deleted) {
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
        return jakarta.ws.rs.core.Response.noContent().build();
    }
}

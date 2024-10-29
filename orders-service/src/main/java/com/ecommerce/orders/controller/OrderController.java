package com.ecommerce.orders.controller;
import com.ecommerce.orders.entity.Order;
import com.ecommerce.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing orders.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Creates a new order.
     *
     * @param order the order to create
     * @return ResponseEntity with the created order
     */
    @PostMapping
    public Mono<ResponseEntity<Order>> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    /**
     * Retrieves an order by ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return ResponseEntity with the retrieved order
     */
    @GetMapping("/{orderId}")
    public Mono<ResponseEntity<Order>> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    /**
     * Retrieves all orders.
     *
     * @return Flux of orders
     */
    @GetMapping
    public Flux<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Updates an existing order.
     *
     * @param orderId the ID of the order to update
     * @param order   the order with updated details
     * @return ResponseEntity with the updated order
     */
    @PutMapping("/{orderId}")
    public Mono<ResponseEntity<Order>> updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, order);
    }

    /**
     * Deletes an order by ID.
     *
     * @param orderId the ID of the order to delete
     * @return ResponseEntity indicating the outcome of the deletion
     */
    @DeleteMapping("/{orderId}")
    public Mono<ResponseEntity<Void>> deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }
}

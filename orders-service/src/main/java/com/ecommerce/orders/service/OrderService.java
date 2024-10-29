package com.ecommerce.orders.service;
import com.ecommerce.orders.entity.Order;
import com.ecommerce.orders.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class for managing orders.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Creates a new order.
     *
     * @param order the order to create
     * @return ResponseEntity with the created order
     */
    public Mono<ResponseEntity<Order>> createOrder(Order order) {
        return orderRepository.save(order)
                .map(savedOrder -> ResponseEntity.ok(savedOrder));
    }

    /**
     * Retrieves an order by ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return ResponseEntity with the retrieved order
     */
    public Mono<ResponseEntity<Order>> getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> ResponseEntity.ok(order))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all orders.
     *
     * @return Flux of orders
     */
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Updates an existing order.
     *
     * @param orderId the ID of the order to update
     * @param order   the order with updated details
     * @return ResponseEntity with the updated order
     */
    public Mono<ResponseEntity<Order>> updateOrder(Long orderId, Order order) {
        return orderRepository.findById(orderId)
                .flatMap(existingOrder -> {
                    existingOrder.setEmail(order.getEmail());
                    existingOrder.setCartItemIds(order.getCartItemIds());
                    existingOrder.setOrderDate(order.getOrderDate());
                    existingOrder.setPaymentId(order.getPaymentId());
                    existingOrder.setTotalAmount(order.getTotalAmount());
                    existingOrder.setOrderStatus(order.getOrderStatus());
                    existingOrder.setAddressId(order.getAddressId());
                    return orderRepository.save(existingOrder)
                            .map(updatedOrder -> ResponseEntity.ok(updatedOrder));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Deletes an order by ID.
     *
     * @param orderId the ID of the order to delete
     * @return ResponseEntity indicating the outcome of the deletion
     */
    public Mono<ResponseEntity<Void>> deleteOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .flatMap(existingOrder -> orderRepository.delete(existingOrder)
                        .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

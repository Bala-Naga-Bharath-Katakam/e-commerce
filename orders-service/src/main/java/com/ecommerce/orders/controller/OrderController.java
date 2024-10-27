package com.ecommerce.orders.controller;

import com.ecommerce.orders.entity.Order;
import com.ecommerce.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public Mono<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id);
    }

    @GetMapping
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

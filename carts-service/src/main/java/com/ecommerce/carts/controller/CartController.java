package com.ecommerce.carts.controller;

import com.ecommerce.carts.entity.Cart;
import com.ecommerce.carts.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @PostMapping
    public Mono<Cart> createCart(@RequestBody Cart cart) {
        return cartRepository.save(cart);
    }

    @GetMapping("/{id}")
    public Mono<Cart> getCartById(@PathVariable Long id) {
        return cartRepository.findById(id);
    }
}


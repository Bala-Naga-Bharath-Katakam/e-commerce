package com.ecommerce.products.controller;

import com.ecommerce.products.clients.AuthClient;
import com.ecommerce.products.entity.Product;
import com.ecommerce.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthClient authClient;

    @PostMapping
    public Mono<ResponseEntity<Product>> createProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Product product) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return productRepository.save(product).map(savedProduct -> ResponseEntity.ok(savedProduct));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Product> getAllProducts(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Flux.error(new RuntimeException("Unauthorized"));
        }

        return productRepository.findAll();
    }
}

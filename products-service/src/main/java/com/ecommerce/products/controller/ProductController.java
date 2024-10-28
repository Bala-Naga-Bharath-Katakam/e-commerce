package com.ecommerce.products.controller;

import com.ecommerce.products.clients.AuthClient;
import com.ecommerce.products.entity.Product;
import com.ecommerce.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "APIs for managing products in the eCommerce application")
public class ProductController {

    @Autowired
    private AuthClient authClient;

    @Autowired
    private ProductService productService;

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return a Mono containing the created product as ResponseEntity
     */
    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product in the system")
    public Mono<ResponseEntity<Product>> createProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                       @RequestBody Product product) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return productService.createProduct(product);
    }

    /**
     * Retrieves all products.
     *
     * @return a Flux containing all products
     */
    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    public Flux<ResponseEntity<Product>> getAllProducts(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Flux.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return productService.getAllProducts();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return a Mono containing the product if found, or 404 Not Found if not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID", description = "Retrieves a product by its unique ID")
    public Mono<ResponseEntity<Product>> getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                        @Parameter(description = "ID of the product to retrieve") @PathVariable Long id) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return productService.getProductById(id);
    }

    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param product the new product data
     * @return a Mono containing the updated product as ResponseEntity, or 404 Not Found if not found
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product", description = "Updates a product by its ID")
    public Mono<ResponseEntity<Product>> updateProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                       @Parameter(description = "ID of the product to update") @PathVariable Long id,
            @RequestBody Product product) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return productService.updateProduct(id, product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return a Mono containing ResponseEntity with a 204 No Content status if deleted, or 404 Not Found if not found
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by its unique ID")
    public Mono<ResponseEntity<Void>> deleteProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                    @Parameter(description = "ID of the product to delete") @PathVariable Long id) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return productService.deleteProduct(id);
    }
}

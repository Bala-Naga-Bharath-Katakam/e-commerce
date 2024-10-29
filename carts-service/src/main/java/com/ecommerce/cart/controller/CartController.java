package com.ecommerce.cart.controller;

import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller for handling cart-related API requests.
 */
@RestController
@RequestMapping("/api/public/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Creates a new cart.
     *
     * @param cart the cart to be created
     * @return a Mono containing the created cart
     */
    @Operation(summary = "Create a new cart", description = "Creates a new cart for the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid cart data")
    })
    @PostMapping
    public Mono<ResponseEntity<Cart>> createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    /**
     * Retrieves a cart by user ID.
     *
     * @param userId the user ID
     * @return a Mono containing the user's cart
     */
    @Operation(summary = "Get cart by user ID", description = "Retrieves the cart for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Cart not found for the user")
    })
    @GetMapping("/{userId}")
    public Mono<ResponseEntity<Cart>> getCartByUserId(
            @Parameter(description = "User ID for whom the cart is being retrieved", required = true)
            @PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    // Additional endpoints for updating and deleting carts can be added here
}



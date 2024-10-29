package com.ecommerce.cart.items.controller;

import com.ecommerce.cart.items.entity.CartItem;
import com.ecommerce.cart.items.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing cart items.
 */
@RestController
@RequestMapping("/api/public/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    /**
     * Adds a new item to the cart.
     *
     * @param cartItem the cart item to be added
     * @return a Mono containing the ResponseEntity with the added cart item
     */
    @Operation(summary = "Add a cart item",
            description = "Adds a new item to the cart.")
    @PostMapping
    public Mono<ResponseEntity<CartItem>> addCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.addCartItem(cartItem);
    }

    /**
     * Retrieves all items in a specified cart.
     *
     * @param cartId the ID of the cart
     * @return a Flux containing ResponseEntity of the cart items
     */
    @Operation(summary = "Get cart items by cart ID",
            description = "Retrieves all items in a specified cart.",
            parameters = {
                    @Parameter(name = "cartId", description = "ID of the cart", required = true)
            })
    @GetMapping("/cart/{cartId}")
    public Flux<ResponseEntity<CartItem>> getCartItemsByCartId(@PathVariable Long cartId) {
        return cartItemService.getCartItemsByCartId(cartId);
    }

    /**
     * Deletes a cart item by its ID.
     *
     * @param cartItemId the ID of the cart item to delete
     * @return a Mono containing ResponseEntity indicating the completion of the delete operation
     */
    @Operation(summary = "Delete a cart item",
            description = "Deletes a cart item by its ID.",
            parameters = {
                    @Parameter(name = "cartItemId", description = "ID of the cart item to delete", required = true)
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cart item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    @DeleteMapping("/{cartItemId}")
    public Mono<ResponseEntity<Object>> deleteCartItem(@PathVariable Long cartItemId) {
        return cartItemService.deleteCartItem(cartItemId);
    }
}

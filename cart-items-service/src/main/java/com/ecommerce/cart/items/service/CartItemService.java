package com.ecommerce.cart.items.service;


import com.ecommerce.cart.items.entity.CartItem;
import com.ecommerce.cart.items.exceptions.ResourceNotFoundException;
import com.ecommerce.cart.items.repository.CartItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Service for managing cart items.
 */
@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * Adds a new cart item to the database.
     *
     * @param cartItem the cart item to add
     * @return a Mono containing the ResponseEntity with the added cart item
     */
    public Mono<ResponseEntity<CartItem>> addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem)
                .map(savedItem -> ResponseEntity.ok(savedItem));
    }

    /**
     * Retrieves all cart items associated with a specific cart ID.
     *
     * @param cartId the ID of the cart
     * @return a Flux containing ResponseEntity of the cart items
     */
    public Flux<ResponseEntity<CartItem>> getCartItemsByCartId(Long cartId) {
        return cartItemRepository.findAll()
                .filter(cartItem -> cartItem.getCartId().equals(cartId))
                .map(ResponseEntity::ok);
    }

    /**
     * Deletes a cart item by its ID.
     *
     * @param cartItemId the ID of the cart item
     * @return a Mono containing ResponseEntity indicating completion
     */
    public Mono<ResponseEntity<Object>> deleteCartItem(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .flatMap(existingItem -> cartItemRepository.delete(existingItem)
                        .then(Mono.just(ResponseEntity.noContent().build())))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("CartItem not found")));
    }

    // Additional methods can be added as needed
}


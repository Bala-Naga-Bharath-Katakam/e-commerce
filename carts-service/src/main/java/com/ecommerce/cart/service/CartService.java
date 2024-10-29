package com.ecommerce.cart.service;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service for managing shopping cart operations.
 */
@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Creates a new cart.
     *
     * @param cart the cart to be created
     * @return a Mono containing the created cart
     */
    public Mono<ResponseEntity<Cart>> createCart(Cart cart) {
        return cartRepository.save(cart)
                .map(savedCart -> ResponseEntity.ok(savedCart));
    }

    /**
     * Retrieves a cart by user ID.
     *
     * @param userId the user ID
     * @return a Mono containing the user's cart
     */
    public Mono<ResponseEntity<Cart>> getCartByUserId(Long userId) {
        return cartRepository.findById(userId)
                .map(cart -> ResponseEntity.ok(cart))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Additional methods for updating and deleting carts can be added here
}


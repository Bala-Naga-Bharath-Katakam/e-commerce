package com.ecommerce.products.service;
import com.ecommerce.products.entity.Product;
import com.ecommerce.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class for managing products.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a new product.
     *
     * @param product the product to be created
     * @return a Mono containing the created product as ResponseEntity
     */
    public Mono<ResponseEntity<Product>> createProduct(Product product) {
        return productRepository.save(product)
                .map(savedProduct -> ResponseEntity.ok(savedProduct));
    }

    /**
     * Retrieves all products.
     *
     * @return a Flux of ResponseEntity containing all products
     */
    public Flux<ResponseEntity<Product>> getAllProducts() {
        return productRepository.findAll()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return a Mono containing the product if found, otherwise 404 Not Found
     */
    public Mono<ResponseEntity<Product>> getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing product.
     *
     * @param productId the ID of the product to update
     * @param product the new product data
     * @return a Mono containing the updated product as ResponseEntity, or 404 Not Found if not found
     */
    public Mono<ResponseEntity<Product>> updateProduct(Long productId, Product product) {
        return productRepository.findById(productId)
                .flatMap(existingProduct -> {
                    existingProduct.setProductName(product.getProductName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setCategoryId(product.getCategoryId());
                    return productRepository.save(existingProduct);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     * @return a Mono containing ResponseEntity with a 204 No Content status if deleted, or 404 Not Found if not found
     */
    public Mono<ResponseEntity<Void>> deleteProduct(Long productId) {
        return productRepository.findById(productId)
                .flatMap(existingProduct ->
                        productRepository.delete(existingProduct)
                                .then(Mono.just(ResponseEntity.noContent().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

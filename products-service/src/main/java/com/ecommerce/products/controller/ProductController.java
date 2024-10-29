package com.ecommerce.products.controller;

import com.ecommerce.products.entity.ProductDTO;
import com.ecommerce.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

/**
 * Controller for managing products.
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Adds a new product.
     *
     * @param productDTO the product data transfer object
     * @return ResponseEntity containing the added ProductDTO
     */
    @PostMapping("/admin/products")
    public Mono<ResponseEntity<ProductDTO>> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    /**
     * Retrieves all products.
     *
     * @return ResponseEntity containing the list of ProductDTOs
     */
    @GetMapping("/public/products")
    public Mono<ResponseEntity<List<ProductDTO>>> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Updates an existing product.
     *
     * @param productId  the ID of the product to be updated
     * @param productDTO the updated product data transfer object
     * @return ResponseEntity containing the updated ProductDTO
     */
    @PutMapping("/admin/products/{productId}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable Long productId,
                                                          @Valid @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productId, productDTO);
    }

    /**
     * Deletes a product.
     *
     * @param productId the ID of the product to be deleted
     * @return ResponseEntity containing the deleted ProductDTO
     */
    @DeleteMapping("/admin/products/{productId}")
    public Mono<ResponseEntity<ProductDTO>> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    /**
     * Updates the image of a product.
     *
     * @param productId the ID of the product
     * @param image     the new image file
     * @return ResponseEntity containing the updated ProductDTO
     * @throws IOException if there is an error while uploading the image
     */
    @PutMapping("/admin/products/{productId}/image")
    public Mono<ResponseEntity<ProductDTO>> updateProductImage(@PathVariable Long productId,
                                                               @RequestParam("image") MultipartFile image) throws IOException {
        return productService.updateProductImage(productId, image);
    }
}

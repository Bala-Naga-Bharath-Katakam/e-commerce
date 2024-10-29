package com.ecommerce.products.controller;

import com.ecommerce.products.entity.ProductDTO;
import com.ecommerce.products.entity.ProductResponse;
import com.ecommerce.products.exceptions.APIException;
import com.ecommerce.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
     * @return Mono containing ResponseEntity with the added ProductDTO
     */
    @Operation(summary = "Add a new product",
            description = "Creates a new product using the provided product data.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
            ))
    @PostMapping("/admin/products")
    public Mono<ResponseEntity<ProductDTO>> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    /**
     * Retrieves all products.
     *
     * @return Mono containing ResponseEntity with the list of ProductDTOs
     */
    @Operation(summary = "Retrieve all products",
            description = "Fetches a list of all available products.")
    @GetMapping("/public/products")
    public Mono<ResponseEntity<List<ProductDTO>>> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Updates an existing product.
     *
     * @param productId  the ID of the product to be updated
     * @param productDTO the updated product data transfer object
     * @return Mono containing ResponseEntity with the updated ProductDTO
     */
    @Operation(summary = "Update an existing product",
            description = "Updates the product with the given ID using the provided product data.")
    @PutMapping("/admin/products/{productId}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable Long productId,
                                                          @Valid @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productId, productDTO);
    }

    /**
     * Deletes a product.
     *
     * @param productId the ID of the product to be deleted
     * @return Mono containing ResponseEntity with the deleted ProductDTO
     */
    @Operation(summary = "Delete a product",
            description = "Deletes the product with the specified ID.")
    @DeleteMapping("/admin/products/{productId}")
    public Mono<ResponseEntity<ProductDTO>> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    /**
     * Updates the image of a product.
     *
     * @param productId the ID of the product
     * @param image     the new image file
     * @return Mono containing ResponseEntity with the updated ProductDTO
     * @throws IOException if there is an error while uploading the image
     */
    @Operation(summary = "Update product image",
            description = "Updates the image for the product with the specified ID.")
    @PutMapping("/admin/products/{productId}/image")
    public Mono<ResponseEntity<ProductDTO>> updateProductImage(@PathVariable Long productId,
                                                               @RequestParam("image") MultipartFile image) throws IOException {
        return productService.updateProductImage(productId, image);
    }

    /**
     * Retrieves products matching a keyword, with pagination and sorting options.
     *
     * @param keyword    the keyword to search for in product names
     * @param pageNumber the page number for pagination
     * @param pageSize   the page size for pagination
     * @param sortBy     the field by which to sort the results
     * @param sortOrder  the order of sorting (asc or desc)
     * @return Mono containing ResponseEntity with the ProductResponse, which includes matching products
     */
    @Operation(summary = "Search products by keyword",
            description = "Fetches a paginated list of products based on the provided keyword, with options for pagination and sorting.",
            parameters = {
                    @Parameter(name = "keyword", description = "The keyword to search in product names", required = true),
                    @Parameter(name = "pageNumber", description = "Page number for pagination"),
                    @Parameter(name = "pageSize", description = "Page size for pagination"),
                    @Parameter(name = "sortBy", description = "Field to sort by"),
                    @Parameter(name = "sortOrder", description = "Sort order, either 'asc' or 'desc'")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "No products found", content = @Content)
    })
    @GetMapping("/public/products/keyword/{keyword}")
    public Mono<ResponseEntity<ProductResponse>> getProductsByKeyword(
            @PathVariable String keyword,
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "productName", required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {
        return productService.searchProductByKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new APIException("Products not found with keyword: " + keyword)));
    }
}

package com.ecommerce.user.address.controller;

import com.ecommerce.user.address.clients.AuthClient;
import com.ecommerce.user.address.entity.Category;
import com.ecommerce.user.address.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Controller for managing categories.
 */
@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category Management", description = "APIs for managing categories in the eCommerce application")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthClient authClient;

    /**
     * Creates a new category.
     *
     * @param category the category to create
     * @return a ResponseEntity containing the created category
     */
    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new category in the system")
    public Mono<ResponseEntity<Category>> createCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                         @RequestBody Category category) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return categoryService.createCategory(category);
    }

    /**
     * Retrieves all categories.
     *
     * @return a ResponseEntity containing a list of categories
     */
    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieves a list of all categories")
    public Mono<ResponseEntity<List<Category>>> getAllCategories(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return categoryService.getAllCategories();
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to retrieve
     * @return a ResponseEntity containing the category if found, or not found if not
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID", description = "Retrieves a category by its unique ID")
    public Mono<ResponseEntity<Category>> getCategoryById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                          @Parameter(description = "ID of the category to retrieve") @PathVariable Long id) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return categoryService.getCategoryById(id);
    }

    /**
     * Updates an existing category.
     *
     * @param id       the ID of the category to update
     * @param category the new category data
     * @return a ResponseEntity containing the updated category
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category", description = "Updates a category by its ID")
    public Mono<ResponseEntity<Category>> updateCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                         @Parameter(description = "ID of the category to update") @PathVariable Long id,
            @RequestBody Category category) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return categoryService.updateCategory(id, category);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Deletes a category by its unique ID")
    public Mono<ResponseEntity<Object>> deleteCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                       @Parameter(description = "ID of the category to delete") @PathVariable Long id) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return categoryService.deleteCategory(id);
    }
}

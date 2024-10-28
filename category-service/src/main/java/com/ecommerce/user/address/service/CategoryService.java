package com.ecommerce.user.address.service;

import com.ecommerce.user.address.entity.Category;
import com.ecommerce.user.address.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service class for managing categories.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Constructor for CategoryService.
     *
     * @param categoryRepository the category repository
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Creates a new category.
     *
     * @param category the category to create
     * @return a ResponseEntity containing the created category
     */
    public Mono<ResponseEntity<Category>> createCategory(@RequestBody Category category) {
        return categoryRepository.save(category)
                .map(savedCategory -> ResponseEntity.ok(savedCategory));
    }

    /**
     * Retrieves all categories.
     *
     * @return a ResponseEntity containing a list of categories
     */
    public Mono<ResponseEntity<List<Category>>> getAllCategories() {
        return categoryRepository.findAll()
                .collectList()
                .map(categories -> ResponseEntity.ok(categories));
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to retrieve
     * @return a ResponseEntity containing the category if found, or not found if not
     */
    public Mono<ResponseEntity<Category>> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> ResponseEntity.ok(category))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update
     * @param category the new category data
     * @return a ResponseEntity containing the updated category
     */
    public Mono<ResponseEntity<Category>> updateCategory(Long id, @RequestBody Category category) {
        category.setCategoryId(id);
        return categoryRepository.save(category)
                .map(updatedCategory -> ResponseEntity.ok(updatedCategory));
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    public Mono<ResponseEntity<Object>> deleteCategory(Long id) {
        return categoryRepository.findById(id)
                .flatMap(existingCategory ->
                        categoryRepository.delete(existingCategory)
                                .then(Mono.just(ResponseEntity.noContent().build())) // Return ResponseEntity<Void>
                )
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Handle not found case
    }

}

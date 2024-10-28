package com.ecommerce.user.address.repository;

import com.ecommerce.user.address.entity.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {
}

package com.ecommerce.users.repository;

import com.ecommerce.users.entity.UserAddress;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserAddressRepository extends ReactiveCrudRepository<UserAddress, Long> {
    Flux<UserAddress> findByUserId(Long userId);
}



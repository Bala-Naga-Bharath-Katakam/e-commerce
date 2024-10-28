package com.ecommerce.user.address.repository;

import com.ecommerce.user.address.entity.UserAddress;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserAddressRepository extends ReactiveCrudRepository<UserAddress, Long> {
    Flux<UserAddress> findByUserId(Long userId);
}



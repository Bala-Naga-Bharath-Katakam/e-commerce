package com.ecommerce.users.repository;


import com.ecommerce.users.entity.Address;
import com.ecommerce.users.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}

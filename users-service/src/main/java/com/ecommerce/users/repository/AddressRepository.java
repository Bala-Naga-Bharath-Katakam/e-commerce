package com.ecommerce.users.repository;


import com.ecommerce.users.entity.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {
    Flux<Address> findByCity(String city);
}

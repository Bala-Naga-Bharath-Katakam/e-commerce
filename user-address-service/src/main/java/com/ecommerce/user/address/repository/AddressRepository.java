package com.ecommerce.user.address.repository;


import com.ecommerce.user.address.entity.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {
    Flux<Address> findByCity(String city);
}

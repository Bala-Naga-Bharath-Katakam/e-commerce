package com.ecommerce.user.address.service;

import com.ecommerce.user.address.entity.Address;
import com.ecommerce.user.address.entity.UserAddress;
import com.ecommerce.user.address.repository.AddressRepository;
import com.ecommerce.user.address.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    public Mono<ResponseEntity<Address>> addAddress(Address address) {
        return addressRepository.save(address).map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<UserAddress>> addUserAddress(Long userId, Long addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setAddressId(addressId);
        return userAddressRepository.save(userAddress).map(ResponseEntity::ok);
    }


    public Flux<ResponseEntity<Address>> getAddressesByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId)
                .flatMap(userAddress -> addressRepository.findById(userAddress.getAddressId())).map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<Void>> deleteAddress(Long addressId) {
        return addressRepository.deleteById(addressId).map(ResponseEntity::ok);
    }
}

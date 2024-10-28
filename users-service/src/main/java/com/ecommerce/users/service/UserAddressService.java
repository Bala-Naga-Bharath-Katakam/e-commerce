package com.ecommerce.users.service;

import com.ecommerce.users.entity.Address;
import com.ecommerce.users.entity.User;
import com.ecommerce.users.entity.UserAddress;
import com.ecommerce.users.entity.UserDAO;
import com.ecommerce.users.repository.AddressRepository;
import com.ecommerce.users.repository.UserAddressRepository;
import com.ecommerce.users.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    public Mono<ResponseEntity<Address>> addAddress(Address address) {
        return addressRepository.save(address).map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<UserAddress>> addUserAddress(Long userId, Long addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setAddressId(addressId);
        return userAddressRepository.save(userAddress).map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<User>> addUser(ResponseEntity<UserDAO> userDetails) {
        User user = new User();
        user.setUserName(userDetails.getBody().getUserName());
        user.setUserId(userDetails.getBody().getId());
        user.setEmail(userDetails.getBody().getEmail());

        return userRepository.save(user).map(ResponseEntity::ok);
    }

    public Flux<ResponseEntity<Address>> getAddressesByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId)
                .flatMap(userAddress -> addressRepository.findById(userAddress.getAddressId())).map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<Void>> deleteAddress(Long addressId) {
        return addressRepository.deleteById(addressId).map(ResponseEntity::ok);
    }
}

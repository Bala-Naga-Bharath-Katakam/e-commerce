package com.ecommerce.user.address.controller;

import com.ecommerce.user.address.clients.AuthClient;
import com.ecommerce.user.address.entity.Address;
import com.ecommerce.user.address.entity.UserAddress;
import com.ecommerce.user.address.repository.AddressRepository;
import com.ecommerce.user.address.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for managing user addresses.
 */
@RestController
@RequestMapping("/api/user-address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private AddressRepository addressRepository;


    /**
     * Adds a new address.
     *
     * @param token the JWT token
     * @param address the address to add
     * @return Mono<Address> the added address
     */
    @Operation(summary = "Add a new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address added successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/addresses")
    public Mono<ResponseEntity<Address>> addAddress(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                    @RequestBody Address address) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return userAddressService.addAddress(address);
    }

    /**
     * Associates a user with an address.
     *
     * @param token the JWT token
     * @param userId the ID of the user
     * @param addressId the ID of the address
     * @return Mono<ResponseEntity<UserAddress></UserAddress>> the created user address association
     */
    @Operation(summary = "Add a user address association")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User address association created successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/{userId}/addresses/{addressId}")
    public Mono<ResponseEntity<UserAddress>> addUserAddress(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                            @PathVariable Long userId,
                                            @PathVariable Long addressId) {

        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return userAddressService.addUserAddress(userId,addressId);
    }


    /**
     * Retrieves addresses associated with a user.
     *
     * @param token the JWT token
     * @param userId the ID of the user
     * @return Flux<ResponseEntity<Address></Address>> the addresses associated with the user
     */
    @Operation(summary = "Get addresses by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{userId}/addresses")
    public Flux<ResponseEntity<Address>> getAddressesByUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                              @PathVariable Long userId) {
        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Flux.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return userAddressService.getAddressesByUserId(userId);
    }

    /**
     * Deletes an address by ID.
     *
     * @param token the JWT token
     * @param addressId the ID of the address to delete
     * @return Mono<ResponseEntity<Void></Void>> indicating completion of the deletion
     */
    @Operation(summary = "Delete an address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/addresses/{addressId}")
    public Mono<ResponseEntity<Void>> deleteAddress(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                    @PathVariable Long addressId) {

        ResponseEntity<?> authResponse = authClient.validateToken(token);
        if (authResponse.getStatusCode() != HttpStatus.OK) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
        return userAddressService.deleteAddress(addressId);
    }

}

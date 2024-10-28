package com.ecommerce.users.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("addresses")
public class Address {
    @Id
    private Long addressId;
    private String buildingName;
    private String city;
    private String country;
    private String pinCode;
    private String state;
    private String street;

    // Getters and Setters
}

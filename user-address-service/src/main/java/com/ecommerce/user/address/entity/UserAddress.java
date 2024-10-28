package com.ecommerce.user.address.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("user_address")
public class UserAddress {
    @Id
    private Long id; // Assuming you might want an ID for this table as well

    @Column("user_id")  // Ensure the column name matches
    private Long userId;

    @Column("address_id") // Ensure the column name matches
    private Long addressId;
}




package com.ecommerce.users.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("users")
public class User {
    @Id
    private Long id; // Change to Long if `user_id` is Long

    @Column("user_id")
    private Long userId;

    @Column("username") // Correct casing
    private String userName;

    @Column("email") // Correct casing
    private String email;
}


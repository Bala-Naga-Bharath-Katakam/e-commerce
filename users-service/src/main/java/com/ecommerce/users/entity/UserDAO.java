package com.ecommerce.users.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {

    private Long id;

    private String userName;
    private String email;
}

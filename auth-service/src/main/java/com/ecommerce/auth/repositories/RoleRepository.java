package com.ecommerce.auth.repositories;

import com.ecommerce.auth.model.AppRole;
import com.ecommerce.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}

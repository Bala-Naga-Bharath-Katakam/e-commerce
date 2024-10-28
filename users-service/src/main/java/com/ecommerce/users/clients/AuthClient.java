package com.ecommerce.users.clients;
import com.ecommerce.users.entity.UserDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", path = "/api/auth")
public interface AuthClient {

    @GetMapping("/validate")
    ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token);

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDAO> getUserDetails(@PathVariable Long userId);
}

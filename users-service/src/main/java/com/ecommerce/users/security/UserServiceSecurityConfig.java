package com.ecommerce.users.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class UserServiceSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Disable CSRF protection for this example
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/users/register").permitAll() // Public endpoint
                        .anyExchange().authenticated() // All other endpoints require authentication
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable); // Disable basic auth if not needed

        return http.build();
    }
}


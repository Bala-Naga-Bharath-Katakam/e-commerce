package com.ecommerce.carts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CartsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartsServiceApplication.class, args);
	}

}
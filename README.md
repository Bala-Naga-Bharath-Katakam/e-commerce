# eCommerce Application

This is an eCommerce application built using Spring Boot and microservices architecture. The application utilizes various microservices for user management, product management, cart management, and order processing. The services communicate through an API Gateway and are registered with a Eureka Server.

## Architecture Overview

- **Eureka Server**: Service registry for all microservices.
- **API Gateway**: Main entry point for external clients to interact with the microservices.
- **User Service**: Manages user information and authentication.
- **Product Service**: Handles product information and categories.
- **Cart Service**: Manages shopping cart functionality.
- **Order Service**: Processes orders and order items.

## Entities and Relationships

### 1. Users
- **Attributes**: 
  - `user_id`
  - `email`
  - `password`
  - `username`
- **Relationships**:
  - **User-Role**: Many-to-Many relationship through `user_role`.
  - **User-Address**: Many-to-Many relationship through `user_address`.
  - **Carts**: One-to-Many relationship with `carts`, where each user can have multiple carts.
  - **Orders**: One-to-Many relationship with `orders`.

### 2. Roles
- **Attributes**: 
  - `role_id`
  - `role_name`
- **Relationships**:
  - **User-Role**: Many-to-Many relationship with `users`.

### 3. Addresses
- **Attributes**: 
  - `address_id`
  - `building_name`
  - `city`
  - `country`
  - `pincode`
  - `state`
  - `street`
- **Relationships**:
  - **User-Address**: Many-to-Many relationship with `users`.

### 4. Carts
- **Attributes**: 
  - `cart_id`
  - `total_price`
- **Relationships**:
  - **Users**: Each cart belongs to a user.
  - **Cart Items**: One-to-Many relationship with `cart_items`, where each cart can have multiple items.

### 5. Cart Items
- **Attributes**: 
  - `cart_item_id`
  - `discount`
  - `product_price`
  - `quantity`
- **Relationships**:
  - **Carts**: Each cart item belongs to a cart.
  - **Products**: Each cart item is associated with a product.

### 6. Categories
- **Attributes**: 
  - `category_id`
  - `category_name`
- **Relationships**:
  - **Products**: One-to-Many relationship with `products`, as each product belongs to one category.

### 7. Products
- **Attributes**: 
  - `product_id`
  - `description`
  - `discount`
  - `image`
  - `price`
  - `product_name`
  - `quantity`
  - `special_price`
- **Relationships**:
  - **Categories**: Each product belongs to a category.
  - **Sellers**: Linked to a seller via `seller_id`.
  - **Cart Items**: Associated with `cart_items`.
  - **Order Items**: Associated with `order_items`.

### 8. Orders
- **Attributes**: 
  - `order_id`
  - `email`
  - `order_date`
  - `order_status`
  - `total_amount`
- **Relationships**:
  - **Users**: Each order belongs to a user.
  - **Payments**: Linked to a payment via `payment_id`.
  - **Order Items**: One-to-Many relationship with `order_items`.

### 9. Order Items
- **Attributes**: 
  - `order_item_id`
  - `discount`
  - `ordered_product_price`
  - `quantity`
- **Relationships**:
  - **Orders**: Each order item is part of an order.
  - **Products**: Each order item is linked to a product.

### 10. Payments
- **Attributes**: 
  - `payment_id`
  - `payment_method`
- **Relationships**:
  - **Orders**: Associated with an order.

## API Endpoints

### User Service
- `POST /users/register`: Register a new user.
- `GET /users/{id}`: Retrieve user by ID.

### Product Service
- `POST /products`: Create a new product.
- `GET /products/{id}`: Retrieve product by ID.
- `GET /products`: Retrieve all products.

### Cart Service
- `POST /carts`: Create a new cart.
- `GET /carts/{userId}`: Retrieve cart by user ID.

### Order Service
- `POST /orders`: Create a new order.
- `GET /orders/{userId}`: Retrieve orders by user ID.

## Technologies Used

- Spring Boot
- Spring Cloud (Eureka, Gateway, OpenFeign)
- Spring Data JPA
- Spring Security
- JWT for Authentication
- H2 Database (for development) / MySQL (for production)

## Getting Started

1. Clone the repository.
2. Navigate to each microservice directory.
3. Run the services using your IDE or with Maven:
   ```bash
   mvn spring-boot:run

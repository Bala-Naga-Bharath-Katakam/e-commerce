CREATE TABLE orders (
    order_id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    cart_item_ids BIGINT[] NOT NULL, -- Array of CartItem IDs
    order_date DATE NOT NULL,
    payment_id BIGINT, -- Reference to payment ID
    total_amount DECIMAL(10, 2) NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    address_id BIGINT -- Reference to address ID
);

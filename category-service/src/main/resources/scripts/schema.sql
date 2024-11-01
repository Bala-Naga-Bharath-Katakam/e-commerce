CREATE TABLE addresses (
    address_id BIGSERIAL PRIMARY KEY,
    building_name VARCHAR(255),
    city VARCHAR(100),
    country VARCHAR(100),
    pincode VARCHAR(20),
    state VARCHAR(100),
    street VARCHAR(255)
);


CREATE TABLE user_address (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES addresses(address_id) ON DELETE CASCADE
);

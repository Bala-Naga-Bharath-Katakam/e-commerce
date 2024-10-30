-- Drop the existing payments table if it exists
DROP TABLE IF EXISTS payments;

-- Create the new payments table with the updated schema
CREATE TABLE payments (
    payment_id BIGSERIAL PRIMARY KEY,    -- Auto-incrementing unique identifier
    order_id BIGINT NOT NULL,            -- Identifier of the associated order
    user_id BIGINT NOT NULL,             -- Identifier of the user making the payment
    amount NUMERIC(10, 2) NOT NULL,      -- Amount to be paid with two decimal precision
    payment_method VARCHAR(50) NOT NULL, -- Payment method (e.g., "CREDIT_CARD", "WALLET")
    status VARCHAR(20) NOT NULL,         -- Payment status (e.g., "PENDING", "COMPLETED")
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date and time of the payment
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Last update timestamp
    transaction_id VARCHAR(100) UNIQUE,  -- Unique identifier from payment gateway
    is_refundable BOOLEAN DEFAULT FALSE  -- Indicates if the payment is eligible for refund
);

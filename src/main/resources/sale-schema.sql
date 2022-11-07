DROP TABLE IF EXISTS tx_sale;

CREATE TABLE tx_sale (
    id VARCHAR(10) PRIMARY KEY NOT NULL,
    product_id INT4 NOT NULL,
    quantity INT4 NOT NULL,
    price NUMERIC(10,2),
    total_price NUMERIC(12,2),
    created_date TIMESTAMP NOT NULL
)
WITH (
    OIDS=FALSE
);

SELECT * FROM tx_sale;
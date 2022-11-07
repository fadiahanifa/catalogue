DROP TABLE IF EXISTS ms_product;

CREATE TABLE ms_product (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    quantity INT4 NULL,
    category_id VARCHAR(50) NOT NULL,
    -- fullname VARCHAR(50) NOT NULL,
    -- role_id VARCHAR(5) NOT NULL,
    -- call_number VARCHAR(15) NULL,
    created_date TIMESTAMP NOT NULL,
    creator_id INT4 NULL,
    updated_date TIMESTAMP NULL,
    updater_id INT4 NULL,
    deleted_date TIMESTAMP NULL,
    deleter_id INT4 NULL,
    rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
    OIDS=FALSE
);

SELECT * FROM ms_product;
CREATE TABLE IF NOT EXISTS tire (
    id SERIAL PRIMARY KEY,
    fire_number BIGINT NOT NULL,
    brand VARCHAR(50) NOT NULL,
    pressure FLOAT NOT NULL CHECK (pressure >= 0),
    status  VARCHAR(50) NOT NULL,

    CONSTRAINT uk_fire_number UNIQUE (fire_number)
);

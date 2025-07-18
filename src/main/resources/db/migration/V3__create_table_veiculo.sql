CREATE TABLE IF NOT EXISTS vehicle (
    id SERIAL PRIMARY KEY,
    plate VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    mileage INTEGER NOT NULL CHECK (mileage >= 0),
    status VARCHAR(50) NOT NULL,
    tire_quantity INTEGER NOT NULL,

    CONSTRAINT uk_plate UNIQUE (plate)
);

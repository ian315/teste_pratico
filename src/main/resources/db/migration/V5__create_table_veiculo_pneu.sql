CREATE TABLE IF NOT EXISTS vehicle_tire (
    id SERIAL PRIMARY KEY,
    vehicle_id BIGINT NOT NULL,
    tire_id BIGINT NOT NULL,
    tire_position BIGINT NOT NULL,

    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    CONSTRAINT fk_tire FOREIGN KEY (tire_id) REFERENCES tire(id),

    CONSTRAINT uk_tire UNIQUE (tire_id)
);

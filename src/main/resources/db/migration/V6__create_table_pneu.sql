CREATE TABLE IF NOT EXISTS pneu (
    id SERIAL PRIMARY KEY,
    numero_fogo VARCHAR(255) NOT NULL,
    marca_id BIGINT NOT NULL,
    pressao FLOAT NOT NULL CHECK (pressao >= 0),
    status pneu_status_enum NOT NULL,

    CONSTRAINT fk_marca_pneu FOREIGN KEY (marca_id) REFERENCES marca(id)
);

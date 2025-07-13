CREATE TABLE IF NOT EXISTS pneu (
    id SERIAL PRIMARY KEY,
    numero_fogo VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL,
    pressao FLOAT NOT NULL CHECK (pressao >= 0),
    status pneu_status_enum NOT NULL,

    CONSTRAINT uk_numero_fogo UNIQUE (numero_fogo)
);

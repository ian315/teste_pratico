CREATE TABLE IF NOT EXISTS pneu (
    id SERIAL PRIMARY KEY,
    numero_fogo BIGINT NOT NULL,
    marca VARCHAR(50) NOT NULL,
    pressao FLOAT NOT NULL CHECK (pressao >= 0),
    status  VARCHAR(50) NOT NULL,

    CONSTRAINT uk_numero_fogo UNIQUE (numero_fogo)
);

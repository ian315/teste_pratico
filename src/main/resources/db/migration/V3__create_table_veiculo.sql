CREATE TABLE IF NOT EXISTS veiculo (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    quilometragem INTEGER NOT NULL CHECK (quilometragem >= 0),
    status VARCHAR(50) NOT NULL,
    quantidade_pneus INTEGER NOT NULL,

    CONSTRAINT uk_placa UNIQUE (placa)
);

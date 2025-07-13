CREATE TABLE IF NOT EXISTS veiculo (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(50) NOT NULL,
    marca_id BIGINT NOT NULL,
    quilometragem INTEGER NOT NULL CHECK (quilometragem >= 0),
    status VARCHAR(50) NOT NULL,
    quantidade_pneus INTEGER NOT NULL,

    CONSTRAINT fk_marca FOREIGN KEY (marca_id) REFERENCES marca (id),
    CONSTRAINT uk_placa UNIQUE (placa)
);

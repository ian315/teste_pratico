CREATE TABLE IF NOT EXISTS marca (
    id SERIAL PRIMARY KEY,
    nome_marca VARCHAR(50) NOT NULL,
    tipo_marca VARCHAR(50) NOT NULL,

    CONSTRAINT chk_tipo_marca CHECK (tipo_marca IN ('VEICULO', 'PNEU'))
);
CREATE TABLE IF NOT EXISTS veiculo_pneu (
    id SERIAL PRIMARY KEY,
    veiculo_id BIGINT NOT NULL,
    pneu_id BIGINT NOT NULL,
    posicao_pneu BIGINT NOT NULL,

    CONSTRAINT fk_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),
    CONSTRAINT fk_pneu FOREIGN KEY (pneu_id) REFERENCES pneu(id),

    CONSTRAINT uk_pneu UNIQUE (pneu_id)
);

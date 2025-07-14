package com.frota.teste_pratico.dto.veiculo_pneu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertPneuVeiculoRequest {

    private Long veiculoId;
    private Long pneuId;
    private int posicao;
}

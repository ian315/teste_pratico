package com.frota.teste_pratico.dto.veiculo_pneu;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InserirPneuVeiculoRequest {

    @NotNull(message = "VeiculoId do pneu não pode ser nulo.")
    private Long veiculoId;

    @NotNull(message = "PneuId pneu não pode ser nulo.")
    private Long pneuId;

    @NotNull(message = "posição não pode ser nulo.")
    private int posicao;
}

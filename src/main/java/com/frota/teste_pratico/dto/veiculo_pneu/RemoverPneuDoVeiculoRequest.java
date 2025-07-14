package com.frota.teste_pratico.dto.veiculo_pneu;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoverPneuDoVeiculoRequest {

    @NotNull(message = "ID do veículo não pode ser nulo.")
    private Long veiculoId;

    @NotNull(message = "ID do pneu não pode ser nulo.")
    private Long pneuId;
}

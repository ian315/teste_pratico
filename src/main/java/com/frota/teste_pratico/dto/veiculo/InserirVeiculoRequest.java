package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InserirVeiculoRequest {

    @NotNull(message = "Placa não pode ser nulo.")
    private String placa;

    @NotNull(message = "Marca não pode ser nulo.")
    private String marca;

    @NotNull(message = "Quilometragem não pode ser nulo.")
    private int quilometragem;

    @NotNull(message = "Status não pode ser nulo.")
    private VeiculoStatusEnum status;

    @NotNull(message = "quantidade de pneus não pode ser nulo.")
    private int quantidadeDePneus;
}

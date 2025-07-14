package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InserirVeiculoRequest {

    private String placa;
    private String marca;
    private int quilometragem;
    private VeiculoStatusEnum status;
    private int quantidadeDePneus;
}

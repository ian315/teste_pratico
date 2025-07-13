package com.frota.teste_pratico.dto;

import com.frota.teste_pratico.model.entities.Marca;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InserirVeiculoRequest {

    private String placa;
    private Marca marca;
    private int quilometragem;
    private VeiculoStatusEnum status;
    private int quantidadeDePneus;

    public InserirVeiculoRequest(Veiculo veiculo) {
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.quilometragem = veiculo.getQuilometragem();
        this.status = veiculo.getStatus();
        this.quantidadeDePneus = veiculo.getQuantidadeDePneus();
    }
}

package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.dto.veiculo_pneu.BuscarVeiculoPneuResponseSemVeiculo;
import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarVeiculoPorPlacaComPneusResponse {

    private String placa;
    private String marca;
    private int quilometragem;
    private VeiculoStatusEnum status;
    private int quantidadeDePneus;
    private List<BuscarVeiculoPneuResponseSemVeiculo> pneuList;
}

package com.frota.teste_pratico.dto.veiculo_pneu;

import com.frota.teste_pratico.dto.pneu.BuscarPneuSemVeiculoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindVeiculoPneuResponseSemVeiculo {

    private BuscarPneuSemVeiculoResponse pneu;
    private int posicao;

}

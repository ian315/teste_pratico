package com.frota.teste_pratico.dto.veiculo_pneu;

import com.frota.teste_pratico.dto.pneu.FindPneuResponseSemVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindVeiculoPneuResponseSemVeiculo {

    private FindPneuResponseSemVeiculo pneu;
    private int posicao;
}

package com.frota.teste_pratico.dto.pneu;

import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPneuResponseSemVeiculo {

    private Long numeroFogo;
    private String marca;
    private Float pressao;
    private PneuStatusEnum status;
}

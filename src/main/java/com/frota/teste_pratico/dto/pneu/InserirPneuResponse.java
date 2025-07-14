package com.frota.teste_pratico.dto.pneu;

import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InserirPneuResponse {

    private Long id;
    private String numeroFogo;
    private String marca;
    private Float pressao;
    private PneuStatusEnum status;
}

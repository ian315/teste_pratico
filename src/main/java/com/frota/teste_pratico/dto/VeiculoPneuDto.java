package com.frota.teste_pratico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoPneuDto {

    private VeiculoDto veiculoDto;
    private PneuDto pneuDto;
    private char posicao;
}

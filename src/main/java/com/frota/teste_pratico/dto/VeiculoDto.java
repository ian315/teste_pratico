package com.frota.teste_pratico.dto;

import com.frota.teste_pratico.model.enums.veiculoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDto {

    private int id;
    private String placa;
    private String marca;
    private int quilometragem;
    private veiculoStatusEnum STATUS;
}

package com.frota.teste_pratico.main.dto;

import com.frota.teste_pratico.main.model.enums.veiculoStatusEnum;
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

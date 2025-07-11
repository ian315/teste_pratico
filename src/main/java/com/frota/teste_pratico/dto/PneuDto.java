package com.frota.teste_pratico.dto;

import com.frota.teste_pratico.model.enums.pneuStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PneuDto {

    private int id;
    private String numeroDeFogo;
    private String marca;
    private Float pressao;
    private pneuStatusEnum STATUS;
}

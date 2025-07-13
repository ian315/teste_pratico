package com.frota.teste_pratico.dto;

import com.frota.teste_pratico.model.entities.Marca;
import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PneuDto {

    private Long id;
    private String numeroDeFogo;
    private Marca marca;
    private Float pressao;
    private PneuStatusEnum STATUS;
}

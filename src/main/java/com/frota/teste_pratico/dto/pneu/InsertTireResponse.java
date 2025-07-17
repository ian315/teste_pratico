package com.frota.teste_pratico.dto.pneu;

import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertTireResponse {

    private Long id;
    private String fireNumber;
    private String brand;
    private Float pressure;
    private PneuStatusEnum status;
}

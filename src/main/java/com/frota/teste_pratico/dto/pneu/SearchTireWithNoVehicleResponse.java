package com.frota.teste_pratico.dto.pneu;

import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTireWithNoVehicleResponse {

    private Long fireNumber;
    private String brand;
    private Float pressure;
    private PneuStatusEnum status;
}

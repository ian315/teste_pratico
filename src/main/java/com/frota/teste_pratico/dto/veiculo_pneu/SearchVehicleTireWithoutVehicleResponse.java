package com.frota.teste_pratico.dto.veiculo_pneu;

import com.frota.teste_pratico.dto.pneu.SearchTireWithNoVehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchVehicleTireWithoutVehicleResponse {

    private SearchTireWithNoVehicleResponse tire;
    private int position;
}

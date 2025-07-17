package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.dto.veiculo_pneu.SearchVehicleTireWithoutVehicleResponse;
import com.frota.teste_pratico.model.enums.VehicleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchVehicleByPlateWithTiresResponse {

    private String plate;
    private String brand;
    private int mileage;
    private VehicleStatusEnum status;
    private int tireQuantity;
    private List<SearchVehicleTireWithoutVehicleResponse> tireList;
}

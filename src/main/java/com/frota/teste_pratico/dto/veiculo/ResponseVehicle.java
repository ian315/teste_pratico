package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.model.enums.VehicleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVehicle {

    private Long id;
    private String plate;
    private String brand;
    private int mileage;
    private VehicleStatusEnum status;
    private int tireQuantity;
}

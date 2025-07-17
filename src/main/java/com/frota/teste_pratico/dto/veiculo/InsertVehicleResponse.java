package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertVehicleResponse {

    private Long id;
    private String plate;
    private String brand;
    private int mileage;
    private VeiculoStatusEnum status;
    private int tireQuantity;
}

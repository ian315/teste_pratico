package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.model.enums.VehicleStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertVehicleRequest {

    @NotNull(message = "Placa não pode ser nulo.")
    private String plate;

    @NotNull(message = "Marca não pode ser nulo.")
    private String brand;

    @NotNull(message = "Quilometragem não pode ser nulo.")
    private int mileage;

    @NotNull(message = "Status não pode ser nulo.")
    private VehicleStatusEnum status;

    @NotNull(message = "quantidade de pneus não pode ser nulo.")
    private int tireQuantity;
}

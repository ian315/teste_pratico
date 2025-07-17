package com.frota.teste_pratico.dto.veiculo;

import com.frota.teste_pratico.dto.veiculo_pneu.BuscarVeiculoPneuResponseSemVeiculo;
import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
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
    private VeiculoStatusEnum status;
    private int tireQuantity;
    private List<BuscarVeiculoPneuResponseSemVeiculo> tireList;
}

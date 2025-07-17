package com.frota.teste_pratico.dto.veiculo_pneu;

import com.frota.teste_pratico.dto.pneu.PneuResponse;
import com.frota.teste_pratico.dto.veiculo.ResponseVehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertTireOnVehicleValidatingPositionResponse {

    private ResponseVehicle veiculo;
    private PneuResponse pneu;
    private int posicao;
}

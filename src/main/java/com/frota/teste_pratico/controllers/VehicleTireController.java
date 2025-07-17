package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.veiculo_pneu.InsertTireOnVehicleValidatingPositionResponse;
import com.frota.teste_pratico.dto.veiculo_pneu.InsertTireOnVehicleRequest;
import com.frota.teste_pratico.dto.veiculo_pneu.removeTireFromVehicleRequest;
import com.frota.teste_pratico.service.VehicleTireService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fleet/vehicle-tire")
public class VehicleTireController {

    @Autowired
    private VehicleTireService service;

    //5. Endpoint para vincular um pneu em um veículo
    @PostMapping("/insert")
    public ResponseEntity<InsertTireOnVehicleValidatingPositionResponse> adicionarPneuAoVeiculo(
            @Valid @RequestBody InsertTireOnVehicleRequest request) {
        return new ResponseEntity<>(service.inserirPneuEmVeiculoComPosicao(request), HttpStatus.OK);
    }

    //6. Endpoint para desvincular um pneu em um veículo
    @DeleteMapping("/remove-tire")
    public ResponseEntity<Void> removerPneuVeiculo(
            @Valid @RequestBody removeTireFromVehicleRequest request) {

        service.removerPneuDoVeiculo(request);
        return ResponseEntity.ok().build();
    }
}

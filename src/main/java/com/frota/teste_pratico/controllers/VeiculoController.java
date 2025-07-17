package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.veiculo.InsertVehicleResponse;
import com.frota.teste_pratico.dto.veiculo.SearchAllVehiclesResponse;
import com.frota.teste_pratico.dto.veiculo.SearchVehicleByPlateWithTiresResponse;
import com.frota.teste_pratico.dto.veiculo.InsertVehicleRequest;
import com.frota.teste_pratico.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fleet/vehicle")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    //1. Endpoint para inserir um veículo específico
    @PostMapping(path = "/insert")
    public ResponseEntity<InsertVehicleResponse> cadastraVeiculo(
            @Valid @RequestBody InsertVehicleRequest veiculoRequest) {

        return new ResponseEntity<>(service.cadastrarVeiculo(veiculoRequest), HttpStatus.OK);
    }

    //2. Endpoint para consultar todos os veículos (uma listagem)
    @GetMapping("/findAll")
    public ResponseEntity<List<SearchAllVehiclesResponse>> buscaTodosVeiculos() {

        return new ResponseEntity<>(service.buscarTodosVeiculos(), HttpStatus.OK);
    }

    //3. Endpoint para consultar um veículo específico (com pneus)
    @GetMapping("/findby/{id}")
    public ResponseEntity<SearchVehicleByPlateWithTiresResponse> buscaVeiculoPorPlaca(@Valid @PathVariable Long id) {

        return new ResponseEntity<>(service.buscarVeiculoPorId(id), HttpStatus.OK);
    }
}

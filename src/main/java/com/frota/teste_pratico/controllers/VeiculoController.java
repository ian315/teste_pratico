package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.veiculo.BuscarTodosVeiculosResponse;
import com.frota.teste_pratico.dto.veiculo.BuscarVeiculoPorPlacaComPneusResponse;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoResponse;
import com.frota.teste_pratico.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frota/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    //1. Endpoint para inserir um veículo específico
    @PostMapping(path = "/inserir")
    public ResponseEntity<InserirVeiculoResponse> cadastraVeiculo(
            @Valid @RequestBody InserirVeiculoRequest veiculoRequest) {

        return new ResponseEntity<>(service.cadastraVeiculo(veiculoRequest), HttpStatus.OK);
    }

    //2. Endpoint para consultar todos os veículos (uma listagem)
    @GetMapping("/findall")
    public ResponseEntity<List<BuscarTodosVeiculosResponse>> buscaTodosVeiculos() {
        return new ResponseEntity<>(service.buscaTodosVeiculos(), HttpStatus.OK);
    }

    //3. Endpoint para consultar um veículo específico (com pneus)
    @GetMapping("findby")
    public ResponseEntity<BuscarVeiculoPorPlacaComPneusResponse> getVeiculoPorPlaca(@RequestParam("id") Long id) {

        return new ResponseEntity<>(service.getVeiculoById(id), HttpStatus.OK);
    }
}

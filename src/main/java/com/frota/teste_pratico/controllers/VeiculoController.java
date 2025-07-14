package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.veiculo.FindAllVeiculosResponse;
import com.frota.teste_pratico.dto.veiculo.FindVeiculoByPlacaWithPneusResponse;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoResponse;
import com.frota.teste_pratico.service.VeiculoService;
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

    //3. Endpoint para inserir um veículo específico
    @PostMapping(path = "/inserir")
    public ResponseEntity<InserirVeiculoResponse> cadastraVeiculo(@RequestBody InserirVeiculoRequest veiculoRequest) {

        return new ResponseEntity<>(service.cadastraVeiculo(veiculoRequest), HttpStatus.OK);
    }

    //1. Endpoint para consultar todos os veículos (uma listagem)
    @GetMapping("/findall")
    public ResponseEntity<List<FindAllVeiculosResponse>> buscaTodosVeiculos() {
        return new ResponseEntity<>(service.buscaTodosVeiculos(), HttpStatus.OK);
    }

//    2. Endpoint para consultar um veículo específico (com pneus)
    @GetMapping("findby")
    public ResponseEntity<FindVeiculoByPlacaWithPneusResponse> GetVeiculoByPlaca(@RequestParam("placa") String placa) {

        return new ResponseEntity<>(service.getVeiculoByPlaca(placa), HttpStatus.OK);
    }
}

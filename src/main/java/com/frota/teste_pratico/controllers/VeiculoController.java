package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.InserirVeiculoResponse;
import com.frota.teste_pratico.dto.VeiculoPneuDto;
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
    //toda endpoint tem seu proprio DTO
    //todo endpoint tera seu pro prio dto de request e de response
    //1. Endpoint para consultar todos os veículos (uma listagem)
    @GetMapping("/findAll")
    public ResponseEntity<List<InserirVeiculoResponse>> buscaTodosVeiculos() {
        return new ResponseEntity<>(service.buscaTodosVeiculos(), HttpStatus.OK);
    }

    //2. Endpoint para consultar um veículo específico (com pneus)
//    @GetMapping("/{placa}")
//    public ResponseEntity<VeiculoPneuDto> GetVeiculoByPlaca(@PathVariable("placa") String placa) {
//
//        return new ResponseEntity<>(placa, HttpStatus.OK);
//    }

    //3. Endpoint para inserir um veículo específico
    @PostMapping(path = "/inserir")
    public ResponseEntity<InserirVeiculoResponse> cadastraVeiculo(@RequestBody InserirVeiculoRequest veiculoRequest) {

        return new ResponseEntity<>(service.cadastraVeiculo(veiculoRequest), HttpStatus.OK);
    }
}

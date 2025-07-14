package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.service.VeiculoPneuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frota/veiculo-pneu")
public class VeiculoPneuController {

    @Autowired
    private VeiculoPneuService service;

//    //5. Endpoint para vincular um pneu em um veículo
//    @PostMapping("/inserir-pneu/{placa}")
//    public ResponseEntity<VeiculoPneuDto> adicionarPneuAoVeiculo(
//            @RequestBody VeiculoPneuDto veiculoPneuDto,
//            @PathVariable("placa") String placa) {
//
//        return new ResponseEntity<>(veiculoPneuDto, HttpStatus.OK);
//    }

    //6. Endpoint para desvincular um pneu em um veículo
//    @DeleteMapping("/remover-pneu/{placa}{}")
//    public ResponseEntity<VeiculoPneuDto> removerPneuVeiculo(
//            @RequestBody VeiculoPneuDto veiculoPneuDto,
//            @PathVariable("placa") String placa) {
//
//        return new ResponseEntity<>(veiculoPneuDto, HttpStatus.OK);
//    }
}

package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.veiculo_pneu.InserirPneuNoVeiculoValidandoPosicaoResponse;
import com.frota.teste_pratico.dto.veiculo_pneu.InserirPneuVeiculoRequest;
import com.frota.teste_pratico.dto.veiculo_pneu.RemoverPneuDoVeiculoRequest;
import com.frota.teste_pratico.service.VeiculoPneuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frota/veiculo-pneu")
public class VeiculoPneuController {

    @Autowired
    private VeiculoPneuService service;

    //5. Endpoint para vincular um pneu em um veículo
    @PostMapping("/inserir")
    public ResponseEntity<InserirPneuNoVeiculoValidandoPosicaoResponse> adicionarPneuAoVeiculo(
            @Valid @RequestBody InserirPneuVeiculoRequest request) {
        return new ResponseEntity<>(service.inserirPneuEmVeiculoComPosicao(request), HttpStatus.OK);
    }

    //6. Endpoint para desvincular um pneu em um veículo
    @DeleteMapping("/remover-pneu")
    public ResponseEntity<Void> removerPneuVeiculo(
            @Valid @RequestBody RemoverPneuDoVeiculoRequest request) {

        service.removerPneuDoVeiculo(request);
        return ResponseEntity.ok().build();
    }
}

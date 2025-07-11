package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.VeiculoDto;
import com.frota.teste_pratico.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlVeiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/url")
    public String getVehicle() {

        return null;
    }

    @GetMapping("/url/{placa}")
    public ResponseEntity<AquiRetornaVeiculoComPneusEePosicoes> GetVeiculoByPlaca(@PathVariable Long id) {

//        return ResponseEntity.ok();
        return null;
    }
}

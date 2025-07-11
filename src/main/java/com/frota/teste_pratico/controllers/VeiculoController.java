package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.VeiculoDto;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.service.VeiculoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urlVeiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/url")
    public ResponseEntity<List<VeiculoDto>> buscaTodosVeiculos() {
        List<VeiculoDto> veiculoDtoList = veiculoService.buscaTodosVeiculos();

        return new ResponseEntity<>(veiculoDtoList, HttpStatus.OK);
    }

    @GetMapping("/url/{placa}")
    public ResponseEntity<AquiRetornaVeiculoComPneusEePosicoes> GetVeiculoByPlaca(@PathVariable Long id) {

        return new ResponseEntity<>(AquiRetornaVeiculoComPneusEePosicoes, HttpStatus.OK);
    }

    @PostMapping(path = "veiculo/inserir")
    public ResponseEntity<VeiculoDto> cadastraVeiculo(@RequestBody VeiculoDto veiculoDto) {

        return new ResponseEntity<>(veiculoDto, HttpStatus.OK);
    }
}

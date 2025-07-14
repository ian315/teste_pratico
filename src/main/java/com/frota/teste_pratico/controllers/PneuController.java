package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.pneu.InserirPneuRequest;
import com.frota.teste_pratico.dto.pneu.InserirPneuResponse;
import com.frota.teste_pratico.service.PneuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frota/pneu")
public class PneuController {

    @Autowired
    private PneuService service;

    //4. Endpoint para inserir um pneu específico
    @PostMapping(path = "/inserir")
    public ResponseEntity<InserirPneuResponse> inserirPneu(@RequestBody InserirPneuRequest inserirPneuRequest) {

        return new ResponseEntity<>(service.inserirPneu(inserirPneuRequest), HttpStatus.OK);
    }
}

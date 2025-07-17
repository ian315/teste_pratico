package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.pneu.InsertTireRequest;
import com.frota.teste_pratico.dto.pneu.InsertTireResponse;
import com.frota.teste_pratico.service.PneuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fleet/tire")
public class PneuController {

    @Autowired
    private PneuService service;

    //4. Endpoint para inserir um pneu específico
    @PostMapping(path = "/insert")
    public ResponseEntity<InsertTireResponse> cadastrarPneu(
            @Valid @RequestBody InsertTireRequest insertTireRequest) {

        return new ResponseEntity<>(service.cadastraPneu(insertTireRequest), HttpStatus.OK);
    }
}

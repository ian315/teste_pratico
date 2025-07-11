package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.dto.PneuDto;
import com.frota.teste_pratico.service.PneuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlPneu")
public class PneuController {

    @Autowired
    private PneuService pneuService;

    @PostMapping(name = "/pneu/inserir")
    public ResponseEntity<PneuDto> inserirPneu(@RequestBody PneuDto pneuDto) {
        pneuService.inserirPneu(pneuDto);
        return new ResponseEntity<>(pneuDto,HttpStatus.OK);
    }
}

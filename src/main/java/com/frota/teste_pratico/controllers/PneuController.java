package com.frota.teste_pratico.controllers;

import com.frota.teste_pratico.service.PneuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlPneu")
public class PneuController {

    @Autowired
    private PneuService pneuService;
}

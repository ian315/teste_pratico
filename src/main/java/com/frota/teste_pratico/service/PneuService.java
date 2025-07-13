package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.PneuDto;
import com.frota.teste_pratico.model.entities.Pneu;
import org.springframework.stereotype.Service;

@Service
public class PneuService {
    public void inserirPneu(PneuDto pneuDto) {
    }

    private PneuDto toDto(Pneu pneu) {
        return new PneuDto(
                pneu.getId(),
                pneu.getNumeroFogo(),
                pneu.getMarca(),
                pneu.getPressao(),
                pneu.getStatus()
        );
    }
}

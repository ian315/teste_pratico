package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.pneu.InserirPneuRequest;
import com.frota.teste_pratico.dto.pneu.InserirPneuResponse;
import com.frota.teste_pratico.mapper.PneuMapper;
import com.frota.teste_pratico.model.entities.Pneu;
import com.frota.teste_pratico.model.exceptions.PneuException;
import com.frota.teste_pratico.repository.PneuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PneuService {

    @Autowired
    private PneuMapper pneuMapper;

    @Autowired
    private PneuRepository repo;

    @Transactional
    public InserirPneuResponse cadastraPneu(InserirPneuRequest request) {
        if (repo.findByNumeroFogo(request.getNumeroFogo()).isPresent()) {
            throw new PneuException("Numero de fogo: " + request.getNumeroFogo() + " já existe");
        }

        Pneu pneu = repo.save(pneuMapper.toEntityFromInsertRequest(request));
        return pneuMapper.toResponseDtoFromEntity(pneu);
    }
}

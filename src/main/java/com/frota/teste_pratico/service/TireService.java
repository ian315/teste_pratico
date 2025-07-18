package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.pneu.InsertTireRequest;
import com.frota.teste_pratico.dto.pneu.InsertTireResponse;
import com.frota.teste_pratico.mapper.TireMapper;
import com.frota.teste_pratico.model.entities.Tire;
import com.frota.teste_pratico.model.exceptions.TireException;
import com.frota.teste_pratico.repository.TireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TireService {

    @Autowired
    private TireMapper tireMapper;

    @Autowired
    private TireRepository repo;

    @Transactional
    public InsertTireResponse cadastraPneu(InsertTireRequest request) {
        if (repo.findByFireNumber(request.getFireNumber()).isPresent()) {
            throw new TireException("Numero de fogo: " + request.getFireNumber() + " já existe");
        }

        Tire tire = repo.save(tireMapper.toEntityFromInsertRequest(request));
        return tireMapper.toResponseDtoFromEntity(tire);
    }
}

package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.InserirVeiculoResponse;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private  VeiculoMapper veiculoMapper;

    @Autowired
    private VeiculoRepository repo;

    public InserirVeiculoResponse cadastraVeiculo(InserirVeiculoRequest veiculoRequest) {
        if(veiculoRequest == null)  throw  new DataIntegrityViolationException("null");

        if(repo.findByPlaca(veiculoRequest.getPlaca()).isPresent() ) {
            throw  new DataIntegrityViolationException("Placa: " + veiculoRequest.getPlaca() + " já existente");
        }

        Veiculo veiculo = repo.save(veiculoMapper.toEntity(veiculoRequest));
        return veiculoMapper.toResponseDto(veiculo);
    }

    public List<InserirVeiculoResponse> buscaTodosVeiculos() {
        //adicionar a dependencia do mapStrc
        return repo.findAll().stream()
                .map(veiculoMapper::toResponseDto)
                .collect(Collectors.toList());

    }
}

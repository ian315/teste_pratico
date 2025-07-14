package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo.BuscarTodosVeiculosResponse;
import com.frota.teste_pratico.dto.veiculo.BuscarVeiculoPorPlacaComPneusResponse;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoResponse;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.repository.PneuRepository;
import com.frota.teste_pratico.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private  VeiculoMapper veiculoMapper;

    @Autowired
    private VeiculoRepository repo;

    @Autowired
    private PneuRepository pneuRepository;

    @Transactional
    public InserirVeiculoResponse cadastraVeiculo(InserirVeiculoRequest veiculoRequest) {
        if(repo.findByPlaca(veiculoRequest.getPlaca()).isPresent() ) {
            throw  new DataIntegrityViolationException("Placa: " + veiculoRequest.getPlaca() + " já existente");
        }

        Veiculo veiculo = repo.save(veiculoMapper.toEntityFromInsertRequest(veiculoRequest));
        return veiculoMapper.toResponseDtoFromEntity(veiculo);
    }

    public List<BuscarTodosVeiculosResponse> buscaTodosVeiculos() {

        return repo.findAll().stream()
                .map(veiculoMapper::toFindAllResponseDtoFromEntity)
                .collect(Collectors.toList());
    }

    public BuscarVeiculoPorPlacaComPneusResponse getVeiculoById(Long id) {
        Veiculo veiculo = repo.findById(id)
                .orElseThrow(() -> new DataIntegrityViolationException("o Veiculo de: " + id + " não existe"));

        return veiculoMapper.toFindVeiculoWithPneusResponseFromEntity(veiculo);
    }
}

package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo.SearchAllVehiclesResponse;
import com.frota.teste_pratico.dto.veiculo.SearchVehicleByPlateWithTiresResponse;
import com.frota.teste_pratico.dto.veiculo.InsertVehicleRequest;
import com.frota.teste_pratico.dto.veiculo.InsertVehicleResponse;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.model.exceptions.VeiculoException;
import com.frota.teste_pratico.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private  VeiculoMapper veiculoMapper;

    @Autowired
    private VeiculoRepository repo;

    @Transactional
    public InsertVehicleResponse cadastrarVeiculo(InsertVehicleRequest veiculoRequest) {
        if(repo.findByPlaca(veiculoRequest.getPlate()).isPresent() ) {
            throw new VeiculoException("Placa: " + veiculoRequest.getPlate() + " já existente");
        }

        Veiculo veiculo = repo.save(veiculoMapper.toEntityFromInsertRequest(veiculoRequest));
        return veiculoMapper.toResponseDtoFromEntity(veiculo);
    }

    public List<SearchAllVehiclesResponse> buscarTodosVeiculos() {

        return repo.findAll().stream()
                .map(veiculoMapper::toFindAllResponseDtoFromEntity)
                .collect(Collectors.toList());
    }

    public SearchVehicleByPlateWithTiresResponse buscarVeiculoPorId(Long id) {
        Veiculo veiculo = repo.findById(id)
                .orElseThrow(() -> new VeiculoException("o Veiculo de ID: " + id + " não existe"));

        return veiculoMapper.toFindVeiculoWithPneusResponseFromEntity(veiculo);
    }
}

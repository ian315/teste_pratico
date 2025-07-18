package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo.SearchAllVehiclesResponse;
import com.frota.teste_pratico.dto.veiculo.SearchVehicleByPlateWithTiresResponse;
import com.frota.teste_pratico.dto.veiculo.InsertVehicleRequest;
import com.frota.teste_pratico.dto.veiculo.InsertVehicleResponse;
import com.frota.teste_pratico.mapper.VehicleMapper;
import com.frota.teste_pratico.model.entities.Vehicle;
import com.frota.teste_pratico.model.exceptions.VehicleException;
import com.frota.teste_pratico.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private VehicleRepository repo;

    @Transactional
    public InsertVehicleResponse cadastrarVeiculo(InsertVehicleRequest veiculoRequest) {
        if(repo.findByPlate(veiculoRequest.getPlate()).isPresent() ) {
            throw new VehicleException("Placa: " + veiculoRequest.getPlate() + " já existente");
        }

        Vehicle vehicle = repo.save(vehicleMapper.toEntityFromInsertRequest(veiculoRequest));
        return vehicleMapper.toResponseDtoFromEntity(vehicle);
    }

    public List<SearchAllVehiclesResponse> buscarTodosVeiculos() {

        return repo.findAll().stream()
                .map(vehicleMapper::toFindAllResponseDtoFromEntity)
                .collect(Collectors.toList());
    }

    public SearchVehicleByPlateWithTiresResponse buscarVeiculoPorId(Long id) {
        Vehicle vehicle = repo.findById(id)
                .orElseThrow(() -> new VehicleException("o Veiculo de ID: " + id + " não existe"));

        return vehicleMapper.toFindVeiculoWithPneusResponseFromEntity(vehicle);
    }
}

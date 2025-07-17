package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo_pneu.InsertTireOnVehicleValidatingPositionResponse;
import com.frota.teste_pratico.dto.veiculo_pneu.InsertTireOnVehicleRequest;
import com.frota.teste_pratico.dto.veiculo_pneu.removeTireFromVehicleRequest;
import com.frota.teste_pratico.mapper.TireMapper;
import com.frota.teste_pratico.mapper.VehicleMapper;
import com.frota.teste_pratico.model.entities.Tire;
import com.frota.teste_pratico.model.entities.Vehicle;
import com.frota.teste_pratico.model.entities.VehicleTire;
import com.frota.teste_pratico.model.exceptions.VehicleTireException;
import com.frota.teste_pratico.repository.TireRepository;
import com.frota.teste_pratico.repository.VehicleTireRepository;
import com.frota.teste_pratico.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleTireService {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private TireMapper tireMapper;

    @Autowired
    private VehicleTireRepository vehicleTireRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TireRepository tireRepository;

    @Transactional
    public InsertTireOnVehicleValidatingPositionResponse inserirPneuEmVeiculoComPosicao(InsertTireOnVehicleRequest request) {

        Vehicle vehicle = vehicleRepository.findById(request.getVeiculoId()).orElseThrow(() -> new VehicleTireException("Veiculo não existe"));
        Tire tire = tireRepository.findById(request.getPneuId()).orElseThrow(() -> new VehicleTireException("Pneu não existe"));

        if (vehicle.getTireQuantity() < request.getPosition()) {
            throw new VehicleTireException("Posição Inválida");
        }

        if (vehicleTireRepository.findByVeiculoIdAndPosicao(request.getVeiculoId(), request.getPosition()).isPresent()) {
            throw new VehicleTireException("Ja existe um pneu nessa posição");
        }

        if (vehicleTireRepository.findByPneuId(request.getPneuId()).isPresent()) {
            throw new VehicleTireException("Ja existe um veiculo com esse pneu");
        }

        VehicleTire vehicleTire = new VehicleTire(vehicle, tire, request.getPosition());
        vehicleTireRepository.save(vehicleTire);

        return new InsertTireOnVehicleValidatingPositionResponse(
                        vehicleMapper.toResponseFromInsertPneuEmVeiculo(vehicle),
                        tireMapper.toInsertResponsePneuOnVeiculoFromEntity(tire),
                        request.getPosition());
    }

    @Transactional
    public void removerPneuDoVeiculo(removeTireFromVehicleRequest request) {

        if(vehicleTireRepository.findByVeiculoIdAndPneuId(request.getVeiculoId(), request.getPneuId()).isEmpty())
            throw  new VehicleTireException("Esse veículo e pneu não estão vinculados");

        vehicleTireRepository.deleteByVeiculoIdAndPneuId(request.getVeiculoId(), request.getPneuId());
    }
}

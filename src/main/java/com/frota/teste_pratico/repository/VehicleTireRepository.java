package com.frota.teste_pratico.repository;

import com.frota.teste_pratico.model.entities.VehicleTire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleTireRepository extends JpaRepository<VehicleTire, Long> {

    Optional<VehicleTire> findByPosition(int posicao);

    Optional<VehicleTire> findByVehicleIdAndPosition(Long vehicleId, int position);

    void deleteByVehicleIdAndTireId(Long vehicleId, Long tireId);

    Optional<VehicleTire> findByVehicleIdAndTireId(Long vehicleId, Long tireId);

    Optional<VehicleTire> findByTireId(Long tireId);
}

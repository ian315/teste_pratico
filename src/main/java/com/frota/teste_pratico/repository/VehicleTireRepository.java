package com.frota.teste_pratico.repository;

import com.frota.teste_pratico.model.entities.VehicleTire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleTireRepository extends JpaRepository<VehicleTire, Long> {

    Optional<VehicleTire> findByPosicao(int posicao);

    Optional<VehicleTire> findByVeiculoIdAndPosicao(Long veiculoId, int posicao);

    void deleteByVeiculoIdAndPneuId(Long veiculoId, Long pneuId);

    Optional<VehicleTire> findByVeiculoIdAndPneuId(Long veiculoId, Long pneuId);

    Optional<VehicleTire> findByPneuId(Long pneuId);
}

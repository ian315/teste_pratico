package com.frota.teste_pratico.repository;

import com.frota.teste_pratico.model.entities.VeiculoPneu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


public interface VeiculoPneuRepository extends JpaRepository<VeiculoPneu, Long> {

    Optional<VeiculoPneu> findByPosicao(int posicao);

    Optional<VeiculoPneu> findByVeiculoIdAndPosicao(Long veiculoId, int posicao);

    void deleteByVeiculoIdAndPneuId(Long veiculoId, Long pneuId);
}

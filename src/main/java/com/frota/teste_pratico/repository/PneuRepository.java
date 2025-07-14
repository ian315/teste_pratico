package com.frota.teste_pratico.repository;

import com.frota.teste_pratico.dto.pneu.InserirPneuRequest;
import com.frota.teste_pratico.model.entities.Pneu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PneuRepository extends JpaRepository<Pneu, Long> {

    Optional<Pneu> findByNumeroFogo(Long numeroFogo);
}

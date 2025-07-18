package com.frota.teste_pratico.repository;

import com.frota.teste_pratico.model.entities.Tire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TireRepository extends JpaRepository<Tire, Long> {

    Optional<Tire> findByFireNumber(Long fireNumber);
}

package com.frota.teste_pratico.repository;

import com.frota.teste_pratico.model.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);

    @Query("""
    SELECT v FROM Veiculo v
    JOIN v.pneus
    WHERE v.id = :id
""")
    Veiculo findByIdWithPneus(@Param("id") Long id);
}

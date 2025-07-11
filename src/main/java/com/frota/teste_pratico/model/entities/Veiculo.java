package com.frota.teste_pratico.model.entities;

import com.frota.teste_pratico.model.enums.veiculoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "quilometragem")
    private int quilometragem;// QUILOMETRAGEM (KM)

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private veiculoStatusEnum STATUS;// STATUS (ativo ou inativo)
}

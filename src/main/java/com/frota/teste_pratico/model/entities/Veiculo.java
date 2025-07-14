package com.frota.teste_pratico.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "quilometragem", nullable = false)
    private int quilometragem;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private VeiculoStatusEnum status;

    @Column(name = "quantidade_pneus", nullable = false)
    private int quantidadeDePneus;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<VeiculoPneu> pneus = new ArrayList<>();
}

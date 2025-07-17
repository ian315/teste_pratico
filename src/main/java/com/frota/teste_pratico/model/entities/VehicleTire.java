package com.frota.teste_pratico.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    @JsonBackReference
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "pneu_id", nullable = false)
    private Tire tire;

    @Column(name = "posicao_pneu", nullable = false)
    private int posicao;

    public VehicleTire(Vehicle vehicle, Tire tire, int posicao) {
        this.vehicle = vehicle;
        this.tire = tire;
        this.posicao = posicao;
    }
}

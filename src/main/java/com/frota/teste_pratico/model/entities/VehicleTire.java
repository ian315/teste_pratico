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
    @JoinColumn(name = "vehicle_id", nullable = false)
    @JsonBackReference
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "tire_id", nullable = false)
    private Tire tire;

    @Column(name = "tire_position", nullable = false)
    private int position;

    public VehicleTire(Vehicle vehicle, Tire tire, int position) {
        this.vehicle = vehicle;
        this.tire = tire;
        this.position = position;
    }
}

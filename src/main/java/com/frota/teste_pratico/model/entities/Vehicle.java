package com.frota.teste_pratico.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.frota.teste_pratico.model.enums.VehicleStatusEnum;
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
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", nullable = false)
    private String plate;

    @Column(name = "marca")
    private String brand;

    @Column(name = "quilometragem", nullable = false)
    private int mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private VehicleStatusEnum status;

    @Column(name = "quantidade_pneus", nullable = false)
    private int tireQuantity;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<VehicleTire> tires = new ArrayList<>();
}

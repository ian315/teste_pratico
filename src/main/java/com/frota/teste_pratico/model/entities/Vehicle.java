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
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate", nullable = false)
    private String plate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "mileage", nullable = false)
    private int mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private VehicleStatusEnum status;

    @Column(name = "tire_quantity", nullable = false)
    private int tireQuantity;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<VehicleTire> tires = new ArrayList<>();
}

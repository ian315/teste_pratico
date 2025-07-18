package com.frota.teste_pratico.model.entities;

import com.frota.teste_pratico.model.enums.TireStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tire {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fire_number", nullable = false)
    private Long fireNumber;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "pressure", nullable = false)
    private Float pressure;

    //talvez um ponto de avaliação
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TireStatusEnum status;
}

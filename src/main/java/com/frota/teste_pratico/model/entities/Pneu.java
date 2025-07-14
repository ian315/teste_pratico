package com.frota.teste_pratico.model.entities;

import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pneu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pneu {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_fogo", nullable = false)
    private Long numeroFogo;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "pressao", nullable = false)
    private Float pressao;

    //talvez um ponto de avaliação
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PneuStatusEnum status;
}

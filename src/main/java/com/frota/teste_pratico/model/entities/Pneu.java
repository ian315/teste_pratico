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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_fogo", nullable = false)
    private String numeroFogo;

    @ManyToOne
    @JoinColumn(name = "marca", nullable = false)
    private String marca;

    @Column(name = "quilometragem", nullable = false)
    private Float pressao;

    //talvez um ponto de avaliação
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PneuStatusEnum status;
}

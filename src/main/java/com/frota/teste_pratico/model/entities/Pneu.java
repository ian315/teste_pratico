package com.frota.teste_pratico.model.entities;

import com.frota.teste_pratico.model.enums.pneuStatusEnum;
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
    private int id;

    @Column(name = "numero_fogo")
    private String numeroDeFogo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "quilometragem")
    private Float pressao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private pneuStatusEnum STATUS;
}

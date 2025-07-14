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
public class VeiculoPneu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    @JsonBackReference
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "pneu_id", nullable = false)
    private Pneu pneu;

    @Column(name = "posicao_pneu", nullable = false)
    private int posicao;

    public VeiculoPneu(Veiculo veiculo, Pneu pneu, int posicao) {
        this.veiculo = veiculo;
        this.pneu = pneu;
        this.posicao = posicao;
    }
}

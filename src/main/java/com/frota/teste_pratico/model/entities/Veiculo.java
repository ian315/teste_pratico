package com.frota.teste_pratico.model.entities;

import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

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

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private String marca;

    @Column(name = "quilometragem", nullable = false)
    private int quilometragem;

    //CASO NAO HAJA A POSSIBILIDADE DE ADICIONAR OUTRO STATUS, SERIA MAIS VIÁVEL FAZER UM BOOLEANO
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private VeiculoStatusEnum status;

    //SERIA VIÁVEL FAZER UMA TABELA DE TIPO OU MODELO DO VEICULO PARA NAO DEPENDER QUE O USUÁRIO DEFINA A QUANTIDADE DE RODAS
    @Column(name = "quantidade_pneus", nullable = false)
    private int quantidadeDePneus;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VeiculoPneu> pneus = new ArrayList<>();
}

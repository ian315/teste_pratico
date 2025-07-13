package com.frota.teste_pratico.model.entities;

import com.frota.teste_pratico.model.enums.TipoMarcaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_marca", nullable = false)
    private String nomeMarca;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_marca", nullable = false)
    private TipoMarcaEnum tipoMarca;
}

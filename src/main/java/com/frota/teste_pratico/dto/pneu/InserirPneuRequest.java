package com.frota.teste_pratico.dto.pneu;

import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InserirPneuRequest {

    @NotNull(message = "Número de fogo não pode ser nulo.")
    private Long numeroFogo;

    @NotNull(message = "Marca não pode ser nulo.")
    private String marca;

    @NotNull(message = "Pressão não pode ser nulo.")
    private Float pressao;

    @NotNull(message = "Status não pode ser nulo.")
    private PneuStatusEnum status;
}

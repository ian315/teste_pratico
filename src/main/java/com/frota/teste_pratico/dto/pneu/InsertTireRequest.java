package com.frota.teste_pratico.dto.pneu;

import com.frota.teste_pratico.model.enums.PneuStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertTireRequest {

    @NotNull(message = "Número de fogo não pode ser nulo.")
    private Long fireNumber;

    @NotNull(message = "Marca não pode ser nulo.")
    private String brand;

    @NotNull(message = "Pressão não pode ser nulo.")
    private Float pressure;

    @NotNull(message = "Status não pode ser nulo.")
    private PneuStatusEnum status;
}

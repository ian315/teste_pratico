package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.InserirVeiculoResponse;
import com.frota.teste_pratico.model.entities.Veiculo;
import org.mapstruct.Mapper;

@Mapper
public interface VeiculoMapper {

    Veiculo toEntity(InserirVeiculoRequest inserirVeiculoRequest);

    InserirVeiculoRequest toRequestDto(Veiculo veiculo);

    InserirVeiculoResponse toResponseDto(Veiculo veiculo);
}

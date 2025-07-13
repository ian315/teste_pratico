package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.InserirVeiculoResponse;
import com.frota.teste_pratico.model.entities.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE) // <--- Adicione essas configurações
public interface VeiculoMapper {

    Veiculo toEntity(InserirVeiculoRequest inserirVeiculoRequest);

    InserirVeiculoRequest toRequestDto(Veiculo veiculo);

    InserirVeiculoResponse toResponseDto(Veiculo veiculo);
}

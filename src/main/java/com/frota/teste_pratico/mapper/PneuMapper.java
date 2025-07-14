package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.pneu.InserirPneuRequest;
import com.frota.teste_pratico.dto.pneu.InserirPneuResponse;
import com.frota.teste_pratico.dto.pneu.PneuResponse;
import com.frota.teste_pratico.model.entities.Pneu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PneuMapper {

    Pneu toEntityFromInsertRequest(InserirPneuRequest inserirPneuRequest);

    InserirPneuRequest toRequestDtoFromEntity(Pneu pneu);

    InserirPneuResponse toResponseDtoFromEntity(Pneu pneu);

    PneuResponse toInsertResponsePneuOnVeiculoFromEntity(Pneu pneu);
}

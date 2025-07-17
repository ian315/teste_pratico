package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.pneu.InsertTireRequest;
import com.frota.teste_pratico.dto.pneu.InsertTireResponse;
import com.frota.teste_pratico.dto.pneu.PneuResponse;
import com.frota.teste_pratico.model.entities.Pneu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PneuMapper {

    Pneu toEntityFromInsertRequest(InsertTireRequest insertTireRequest);

    InsertTireRequest toRequestDtoFromEntity(Pneu pneu);

    InsertTireResponse toResponseDtoFromEntity(Pneu pneu);

    PneuResponse toInsertResponsePneuOnVeiculoFromEntity(Pneu pneu);
}

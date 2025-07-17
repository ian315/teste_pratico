package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.pneu.InsertTireRequest;
import com.frota.teste_pratico.dto.pneu.InsertTireResponse;
import com.frota.teste_pratico.dto.pneu.PneuResponse;
import com.frota.teste_pratico.model.entities.Tire;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TireMapper {

    Tire toEntityFromInsertRequest(InsertTireRequest insertTireRequest);

    InsertTireRequest toRequestDtoFromEntity(Tire tire);

    InsertTireResponse toResponseDtoFromEntity(Tire tire);

    PneuResponse toInsertResponsePneuOnVeiculoFromEntity(Tire tire);
}

package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.pneu.SearchTireWithNoVehicleResponse;
import com.frota.teste_pratico.dto.veiculo.*;
import com.frota.teste_pratico.dto.veiculo_pneu.BuscarVeiculoPneuResponseSemVeiculo;
import com.frota.teste_pratico.model.entities.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VeiculoMapper {

    Veiculo toEntityFromInsertRequest(InsertVehicleRequest insertVehicleRequest);

    InsertVehicleRequest toRequestDtoFromEntity(Veiculo veiculo);

    InsertVehicleResponse toResponseDtoFromEntity(Veiculo veiculo);

    SearchAllVehiclesResponse toFindAllResponseDtoFromEntity(Veiculo veiculo);

    @Mapping(target = "pneuList", ignore = true)
    SearchVehicleByPlateWithTiresResponse toFindVeiculoByPlacaWithPneusResponseFromEntity(Veiculo veiculo);

    ResponseVehicle toResponseFromInsertPneuEmVeiculo (Veiculo veiculo);

    default SearchVehicleByPlateWithTiresResponse toFindVeiculoWithPneusResponseFromEntity(Veiculo veiculo){
        if ( veiculo == null ) {
            return null;
        }

        SearchVehicleByPlateWithTiresResponse searchVehicleByPlateWithTiresResponse = new SearchVehicleByPlateWithTiresResponse();

        searchVehicleByPlateWithTiresResponse.setPlate(veiculo.getPlaca());
        searchVehicleByPlateWithTiresResponse.setBrand(veiculo.getMarca());
        searchVehicleByPlateWithTiresResponse.setMileage(veiculo.getQuilometragem());
        searchVehicleByPlateWithTiresResponse.setStatus(veiculo.getStatus());
        searchVehicleByPlateWithTiresResponse.setTireQuantity(veiculo.getQuantidadeDePneus());

        searchVehicleByPlateWithTiresResponse.setTireList(veiculo.getPneus().stream().map(vp ->
        new BuscarVeiculoPneuResponseSemVeiculo(
                        new SearchTireWithNoVehicleResponse(
                vp.getPneu().getNumeroFogo(),
                                vp.getPneu().getMarca(),
                                vp.getPneu().getPressao(),
                                vp.getPneu().getStatus()),
        vp.getPosicao())).toList());

        return searchVehicleByPlateWithTiresResponse;
    }
}

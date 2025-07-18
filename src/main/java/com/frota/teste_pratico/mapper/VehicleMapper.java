package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.pneu.SearchTireWithNoVehicleResponse;
import com.frota.teste_pratico.dto.veiculo.*;
import com.frota.teste_pratico.dto.veiculo_pneu.SearchVehicleTireWithoutVehicleResponse;
import com.frota.teste_pratico.model.entities.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

    Vehicle toEntityFromInsertRequest(InsertVehicleRequest insertVehicleRequest);

    InsertVehicleRequest toRequestDtoFromEntity(Vehicle vehicle);

    InsertVehicleResponse toResponseDtoFromEntity(Vehicle vehicle);

    SearchAllVehiclesResponse toFindAllResponseDtoFromEntity(Vehicle vehicle);

    @Mapping(target = "tireList", ignore = true)
    SearchVehicleByPlateWithTiresResponse toFindVeiculoByPlacaWithPneusResponseFromEntity(Vehicle vehicle);

    ResponseVehicle toResponseFromInsertPneuEmVeiculo (Vehicle vehicle);

    default SearchVehicleByPlateWithTiresResponse toFindVeiculoWithPneusResponseFromEntity(Vehicle vehicle){
        if ( vehicle == null ) {
            return null;
        }

        SearchVehicleByPlateWithTiresResponse searchVehicleByPlateWithTiresResponse = new SearchVehicleByPlateWithTiresResponse();

        searchVehicleByPlateWithTiresResponse.setPlate(vehicle.getPlate());
        searchVehicleByPlateWithTiresResponse.setBrand(vehicle.getBrand());
        searchVehicleByPlateWithTiresResponse.setMileage(vehicle.getMileage());
        searchVehicleByPlateWithTiresResponse.setStatus(vehicle.getStatus());
        searchVehicleByPlateWithTiresResponse.setTireQuantity(vehicle.getTireQuantity());

        searchVehicleByPlateWithTiresResponse.setTireList(vehicle.getTires().stream().map(vp ->
        new SearchVehicleTireWithoutVehicleResponse(
                        new SearchTireWithNoVehicleResponse(
                vp.getTire().getFireNumber(),
                                vp.getTire().getBrand(),
                                vp.getTire().getPressure(),
                                vp.getTire().getStatus()),
        vp.getPosition())).toList());

        return searchVehicleByPlateWithTiresResponse;
    }
}

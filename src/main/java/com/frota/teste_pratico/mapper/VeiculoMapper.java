package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.pneu.BuscarPneuSemVeiculoResponse;
import com.frota.teste_pratico.dto.veiculo.*;
import com.frota.teste_pratico.dto.veiculo_pneu.BuscarVeiculoPneuResponseSemVeiculo;
import com.frota.teste_pratico.model.entities.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VeiculoMapper {

    Veiculo toEntityFromInsertRequest(InserirVeiculoRequest inserirVeiculoRequest);

    InserirVeiculoRequest toRequestDtoFromEntity(Veiculo veiculo);

    InserirVeiculoResponse toResponseDtoFromEntity(Veiculo veiculo);

    BuscarTodosVeiculosResponse toFindAllResponseDtoFromEntity(Veiculo veiculo);

    @Mapping(target = "pneuList", ignore = true)
    BuscarVeiculoPorPlacaComPneusResponse toFindVeiculoByPlacaWithPneusResponseFromEntity(Veiculo veiculo);

    ResponseVeiculo toResponseFromInsertPneuEmVeiculo (Veiculo veiculo);

    default BuscarVeiculoPorPlacaComPneusResponse toFindVeiculoWithPneusResponseFromEntity(Veiculo veiculo){
        if ( veiculo == null ) {
            return null;
        }

        BuscarVeiculoPorPlacaComPneusResponse buscarVeiculoPorPlacaComPneusResponse = new BuscarVeiculoPorPlacaComPneusResponse();

        buscarVeiculoPorPlacaComPneusResponse.setPlaca( veiculo.getPlaca() );
        buscarVeiculoPorPlacaComPneusResponse.setMarca( veiculo.getMarca() );
        buscarVeiculoPorPlacaComPneusResponse.setQuilometragem( veiculo.getQuilometragem() );
        buscarVeiculoPorPlacaComPneusResponse.setStatus( veiculo.getStatus() );

        buscarVeiculoPorPlacaComPneusResponse.setPneuList(veiculo.getPneus().stream().map(vp ->
        new BuscarVeiculoPneuResponseSemVeiculo(
                        new BuscarPneuSemVeiculoResponse(
                vp.getPneu().getNumeroFogo(),
                                vp.getPneu().getMarca(),
                                vp.getPneu().getPressao(),
                                vp.getPneu().getStatus()),
        vp.getPosicao())).toList());

        return buscarVeiculoPorPlacaComPneusResponse;
    }
}

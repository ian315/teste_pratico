package com.frota.teste_pratico.mapper;

import com.frota.teste_pratico.dto.pneu.FindPneuResponseSemVeiculo;
import com.frota.teste_pratico.dto.veiculo.FindAllVeiculosResponse;
import com.frota.teste_pratico.dto.veiculo.FindVeiculoByPlacaWithPneusResponse;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoRequest;
import com.frota.teste_pratico.dto.veiculo.InserirVeiculoResponse;
import com.frota.teste_pratico.dto.veiculo_pneu.FindVeiculoPneuResponseSemVeiculo;
import com.frota.teste_pratico.model.entities.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VeiculoMapper {

    Veiculo toEntityFromInsertRequest(InserirVeiculoRequest inserirVeiculoRequest);

    InserirVeiculoRequest toRequestDtoFromEntity(Veiculo veiculo);

    InserirVeiculoResponse toResponseDtoFromEntity(Veiculo veiculo);

    FindAllVeiculosResponse toFindAllResponseDtoFromEntity(Veiculo veiculo);

    @Mapping(target = "pneuList", ignore = true)
    FindVeiculoByPlacaWithPneusResponse toFindVeiculoByPlacaWithPneusResponseFromEntity(Veiculo veiculo);

    default
    FindVeiculoByPlacaWithPneusResponse toFindVeiculoWithPneusResponseFromEntity(Veiculo veiculo){
        if ( veiculo == null ) {
            return null;
        }

        FindVeiculoByPlacaWithPneusResponse findVeiculoByPlacaWithPneusResponse = new FindVeiculoByPlacaWithPneusResponse();

        findVeiculoByPlacaWithPneusResponse.setPlaca( veiculo.getPlaca() );
        findVeiculoByPlacaWithPneusResponse.setMarca( veiculo.getMarca() );
        findVeiculoByPlacaWithPneusResponse.setQuilometragem( veiculo.getQuilometragem() );
        findVeiculoByPlacaWithPneusResponse.setStatus( veiculo.getStatus() );

        findVeiculoByPlacaWithPneusResponse.setPneuList(veiculo.getPneus().stream().map(vp ->
        new FindVeiculoPneuResponseSemVeiculo(
                        new FindPneuResponseSemVeiculo(
                vp.getPneu().getNumeroFogo(),
                                vp.getPneu().getMarca(),
                                vp.getPneu().getPressao(),
                                vp.getPneu().getStatus()),
        vp.getPosicao())).toList());

        return findVeiculoByPlacaWithPneusResponse;
    }
}

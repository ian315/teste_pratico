package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo.*;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.model.enums.VeiculoStatusEnum;
import com.frota.teste_pratico.model.exceptions.VeiculoException;
import com.frota.teste_pratico.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private VeiculoMapper veiculoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarVeiculoComSucesso() {
        InsertVehicleRequest request = new InsertVehicleRequest();
        request.setPlate("ABC1234");

        Veiculo veiculoEntity = new Veiculo();
        InsertVehicleResponse response = new InsertVehicleResponse();

        when(veiculoRepository.findByPlaca("ABC1234")).thenReturn(Optional.empty());
        when(veiculoMapper.toEntityFromInsertRequest(request)).thenReturn(veiculoEntity);
        when(veiculoRepository.save(veiculoEntity)).thenReturn(veiculoEntity);
        when(veiculoMapper.toResponseDtoFromEntity(veiculoEntity)).thenReturn(response);

        InsertVehicleResponse result = veiculoService.cadastrarVeiculo(request);

        assertEquals(response, result);
        verify(veiculoRepository).save(veiculoEntity);
    }

    @Test
    void deveLancarExcecaoSePlacaJaExistente() {
        InsertVehicleRequest request = new InsertVehicleRequest();
        request.setPlate("XYZ9876");

        when(veiculoRepository.findByPlaca("XYZ9876")).thenReturn(Optional.of(new Veiculo()));

        assertThrows(VeiculoException.class, () -> {
            veiculoService.cadastrarVeiculo(request);
        });

        verify(veiculoRepository, never()).save(any());
    }

    @Test
    void deveRetornarListaDeVeiculos() {
        Veiculo v1 = new Veiculo();
        Veiculo v2 = new Veiculo();

        SearchAllVehiclesResponse r1 = new SearchAllVehiclesResponse();
        SearchAllVehiclesResponse r2 = new SearchAllVehiclesResponse();

        when(veiculoRepository.findAll()).thenReturn(List.of(v1, v2));
        when(veiculoMapper.toFindAllResponseDtoFromEntity(v1)).thenReturn(r1);
        when(veiculoMapper.toFindAllResponseDtoFromEntity(v2)).thenReturn(r2);

        List<SearchAllVehiclesResponse> result = veiculoService.buscarTodosVeiculos();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(r1, r2)));
    }

    @Test
    void deveRetornarVeiculoComPneusQuandoIdExiste() {
        Long id = 1L;
        Veiculo veiculo = new Veiculo();
        SearchVehicleByPlateWithTiresResponse response = new SearchVehicleByPlateWithTiresResponse();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));
        when(veiculoMapper.toFindVeiculoWithPneusResponseFromEntity(veiculo)).thenReturn(response);

        SearchVehicleByPlateWithTiresResponse result = veiculoService.buscarVeiculoPorId(id);

        assertEquals(response, result);
    }

    @Test
    void deveMostrarVeiculoComListaDePneusVaziaSeNaoTiverPneuCadastrado() {
        Long id = 99L;
        Veiculo veiculo = new Veiculo(
                99L, "PlacaTeste","MarcaTeste", 1000, VeiculoStatusEnum.ATIVO, 4, Collections.EMPTY_LIST);

        SearchVehicleByPlateWithTiresResponse dto = new SearchVehicleByPlateWithTiresResponse(
                "PlacaTeste","MarcaTeste", 1000, VeiculoStatusEnum.ATIVO, 4, Collections.EMPTY_LIST);

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));
        when(veiculoMapper.toFindVeiculoWithPneusResponseFromEntity(veiculo)).thenReturn(dto);

        SearchVehicleByPlateWithTiresResponse response = veiculoService.buscarVeiculoPorId(id);

        assertNotNull(response);
        assertTrue(response.getTireList().isEmpty());
    }
}
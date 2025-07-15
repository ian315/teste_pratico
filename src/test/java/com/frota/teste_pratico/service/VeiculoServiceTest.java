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
        InserirVeiculoRequest request = new InserirVeiculoRequest();
        request.setPlaca("ABC1234");

        Veiculo veiculoEntity = new Veiculo();
        InserirVeiculoResponse response = new InserirVeiculoResponse();

        when(veiculoRepository.findByPlaca("ABC1234")).thenReturn(Optional.empty());
        when(veiculoMapper.toEntityFromInsertRequest(request)).thenReturn(veiculoEntity);
        when(veiculoRepository.save(veiculoEntity)).thenReturn(veiculoEntity);
        when(veiculoMapper.toResponseDtoFromEntity(veiculoEntity)).thenReturn(response);

        InserirVeiculoResponse result = veiculoService.cadastrarVeiculo(request);

        assertEquals(response, result);
        verify(veiculoRepository).save(veiculoEntity);
    }

    @Test
    void deveLancarExcecaoSePlacaJaExistente() {
        InserirVeiculoRequest request = new InserirVeiculoRequest();
        request.setPlaca("XYZ9876");

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

        BuscarTodosVeiculosResponse r1 = new BuscarTodosVeiculosResponse();
        BuscarTodosVeiculosResponse r2 = new BuscarTodosVeiculosResponse();

        when(veiculoRepository.findAll()).thenReturn(List.of(v1, v2));
        when(veiculoMapper.toFindAllResponseDtoFromEntity(v1)).thenReturn(r1);
        when(veiculoMapper.toFindAllResponseDtoFromEntity(v2)).thenReturn(r2);

        List<BuscarTodosVeiculosResponse> result = veiculoService.buscarTodosVeiculos();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(r1, r2)));
    }

    @Test
    void deveRetornarVeiculoComPneusQuandoIdExiste() {
        Long id = 1L;
        Veiculo veiculo = new Veiculo();
        BuscarVeiculoPorPlacaComPneusResponse response = new BuscarVeiculoPorPlacaComPneusResponse();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));
        when(veiculoMapper.toFindVeiculoWithPneusResponseFromEntity(veiculo)).thenReturn(response);

        BuscarVeiculoPorPlacaComPneusResponse result = veiculoService.buscarVeiculoPorId(id);

        assertEquals(response, result);
    }

    @Test
    void deveMostrarVeiculoComListaDePneusVaziaSeNaoTiverPneuCadastrado() {
        Long id = 99L;
        Veiculo veiculo = new Veiculo(
                99L, "PlacaTeste","MarcaTeste", 1000, VeiculoStatusEnum.ATIVO, 4, Collections.EMPTY_LIST);

        BuscarVeiculoPorPlacaComPneusResponse dto = new BuscarVeiculoPorPlacaComPneusResponse(
                "PlacaTeste","MarcaTeste", 1000, VeiculoStatusEnum.ATIVO, 4, Collections.EMPTY_LIST);

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));
        when(veiculoMapper.toFindVeiculoWithPneusResponseFromEntity(veiculo)).thenReturn(dto);

        BuscarVeiculoPorPlacaComPneusResponse response = veiculoService.buscarVeiculoPorId(id);

        assertNotNull(response);
        assertTrue(response.getPneuList().isEmpty());
    }
}
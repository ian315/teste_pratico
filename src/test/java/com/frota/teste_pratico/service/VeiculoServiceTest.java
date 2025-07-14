package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo.*;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataIntegrityViolationException;

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

    // ---------- TESTE 1: cadastrar veículo com sucesso ----------
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

        InserirVeiculoResponse result = veiculoService.cadastraVeiculo(request);

        assertEquals(response, result);
        verify(veiculoRepository).save(veiculoEntity);
    }

    // ---------- TESTE 2: tentar cadastrar veículo com placa duplicada ----------
    @Test
    void deveLancarExcecaoSePlacaJaExistente() {
        InserirVeiculoRequest request = new InserirVeiculoRequest();
        request.setPlaca("XYZ9876");

        when(veiculoRepository.findByPlaca("XYZ9876")).thenReturn(Optional.of(new Veiculo()));

        assertThrows(DataIntegrityViolationException.class, () -> {
            veiculoService.cadastraVeiculo(request);
        });

        verify(veiculoRepository, never()).save(any());
    }

    // ---------- TESTE 3: buscar todos os veículos ----------
    @Test
    void deveRetornarListaDeVeiculos() {
        Veiculo v1 = new Veiculo();
        Veiculo v2 = new Veiculo();

        BuscarTodosVeiculosResponse r1 = new BuscarTodosVeiculosResponse();
        BuscarTodosVeiculosResponse r2 = new BuscarTodosVeiculosResponse();

        when(veiculoRepository.findAll()).thenReturn(List.of(v1, v2));
        when(veiculoMapper.toFindAllResponseDtoFromEntity(v1)).thenReturn(r1);
        when(veiculoMapper.toFindAllResponseDtoFromEntity(v2)).thenReturn(r2);

        List<BuscarTodosVeiculosResponse> result = veiculoService.buscaTodosVeiculos();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(r1, r2)));
    }

    // ---------- TESTE 4: buscar veículo por ID com sucesso ----------
    @Test
    void deveRetornarVeiculoComPneusQuandoIdExiste() {
        Long id = 1L;
        Veiculo veiculo = new Veiculo();
        BuscarVeiculoPorPlacaComPneusResponse response = new BuscarVeiculoPorPlacaComPneusResponse();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.findByIdWithPneus(id)).thenReturn(veiculo);
        when(veiculoMapper.toFindVeiculoWithPneusResponseFromEntity(veiculo)).thenReturn(response);

        BuscarVeiculoPorPlacaComPneusResponse result = veiculoService.getVeiculoById(id);

        assertEquals(response, result);
    }

    // ---------- TESTE 5: lançar exceção se veículo não existe ----------
    @Test
    void deveLancarExcecaoSeVeiculoNaoExiste() {
        Long id = 99L;

        when(veiculoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DataIntegrityViolationException.class, () -> {
            veiculoService.getVeiculoById(id);
        });

        verify(veiculoRepository, never()).findByIdWithPneus(id);
    }
}
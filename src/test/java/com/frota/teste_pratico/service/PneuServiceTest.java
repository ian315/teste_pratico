package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.pneu.InserirPneuRequest;
import com.frota.teste_pratico.dto.pneu.InserirPneuResponse;
import com.frota.teste_pratico.mapper.PneuMapper;
import com.frota.teste_pratico.model.entities.Pneu;
import com.frota.teste_pratico.model.exceptions.PneuException;
import com.frota.teste_pratico.repository.PneuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PneuServiceTest {

    @InjectMocks
    private PneuService pneuService;

    @Mock
    private PneuMapper pneuMapper;

    @Mock
    private PneuRepository pneuRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastraPneuComSucesso() {
        InserirPneuRequest request = new InserirPneuRequest();
        request.setNumeroFogo(123L);

        Pneu pneuEntity = new Pneu();
        InserirPneuResponse expectedResponse = new InserirPneuResponse();

        when(pneuRepository.findByNumeroFogo(123L)).thenReturn(Optional.empty());
        when(pneuMapper.toEntityFromInsertRequest(request)).thenReturn(pneuEntity);
        when(pneuRepository.save(pneuEntity)).thenReturn(pneuEntity);
        when(pneuMapper.toResponseDtoFromEntity(pneuEntity)).thenReturn(expectedResponse);

        InserirPneuResponse response = pneuService.cadastraPneu(request);

        assertEquals(expectedResponse, response);
        verify(pneuRepository).save(pneuEntity);
    }

    @Test
    void deveLancarExcecaoQuandoNumeroFogoJaExiste() {
        InserirPneuRequest request = new InserirPneuRequest();
        request.setNumeroFogo(456L);

        when(pneuRepository.findByNumeroFogo(456L)).thenReturn(Optional.of(new Pneu()));

        PneuException exception = assertThrows(PneuException.class, () -> {
            pneuService.cadastraPneu(request);
        });

        assertTrue(exception.getMessage().contains("456"));
        verify(pneuRepository, never()).save(any());
    }
}
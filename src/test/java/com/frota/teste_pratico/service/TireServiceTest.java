package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.pneu.InsertTireRequest;
import com.frota.teste_pratico.dto.pneu.InsertTireResponse;
import com.frota.teste_pratico.mapper.TireMapper;
import com.frota.teste_pratico.model.entities.Tire;
import com.frota.teste_pratico.model.exceptions.TireException;
import com.frota.teste_pratico.repository.TireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TireServiceTest {

    @InjectMocks
    private TireService tireService;

    @Mock
    private TireMapper tireMapper;

    @Mock
    private TireRepository tireRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastraPneuComSucesso() {
        InsertTireRequest request = new InsertTireRequest();
        request.setFireNumber(123L);

        Tire tireEntity = new Tire();
        InsertTireResponse expectedResponse = new InsertTireResponse();

        when(tireRepository.findByNumeroFogo(123L)).thenReturn(Optional.empty());
        when(tireMapper.toEntityFromInsertRequest(request)).thenReturn(tireEntity);
        when(tireRepository.save(tireEntity)).thenReturn(tireEntity);
        when(tireMapper.toResponseDtoFromEntity(tireEntity)).thenReturn(expectedResponse);

        InsertTireResponse response = tireService.cadastraPneu(request);

        assertEquals(expectedResponse, response);
        verify(tireRepository).save(tireEntity);
    }

    @Test
    void deveLancarExcecaoQuandoNumeroFogoJaExiste() {
        InsertTireRequest request = new InsertTireRequest();
        request.setFireNumber(456L);

        when(tireRepository.findByNumeroFogo(456L)).thenReturn(Optional.of(new Tire()));

        TireException exception = assertThrows(TireException.class, () -> {
            tireService.cadastraPneu(request);
        });

        assertTrue(exception.getMessage().contains("456"));
        verify(tireRepository, never()).save(any());
    }
}
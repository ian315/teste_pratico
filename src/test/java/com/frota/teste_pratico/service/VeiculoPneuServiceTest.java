package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.pneu.PneuResponse;
import com.frota.teste_pratico.dto.veiculo.ResponseVehicle;
import com.frota.teste_pratico.dto.veiculo_pneu.*;
import com.frota.teste_pratico.mapper.PneuMapper;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Pneu;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.model.entities.VeiculoPneu;
import com.frota.teste_pratico.model.exceptions.VeiculoPneuException;
import com.frota.teste_pratico.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoPneuServiceTest {

    @InjectMocks
    private VeiculoPneuService veiculoPneuService;

    @Mock private VeiculoMapper veiculoMapper;
    @Mock private PneuMapper pneuMapper;
    @Mock private VeiculoPneuRepository veiculoPneuRepository;
    @Mock private VeiculoRepository veiculoRepository;
    @Mock private PneuRepository pneuRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void deveLancarExcecaoQuandoVeiculoNaoExiste() {
        InserirPneuVeiculoRequest request = new InserirPneuVeiculoRequest(1L, 1L, 1);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VeiculoPneuException.class, () -> veiculoPneuService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveLancarExcecaoQuandoPneuNaoExiste() {
        InserirPneuVeiculoRequest request = new InserirPneuVeiculoRequest(1L, 2L, 1);
        Veiculo veiculo = new Veiculo();
        veiculo.setQuantidadeDePneus(4);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(pneuRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(VeiculoPneuException.class, () -> veiculoPneuService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveLancarExcecaoSePosicaoMaiorQueQuantidadeDePneus() {
        InserirPneuVeiculoRequest request = new InserirPneuVeiculoRequest(1L, 2L, 5);
        Veiculo veiculo = new Veiculo();
        veiculo.setQuantidadeDePneus(4);
        Pneu pneu = new Pneu();

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(pneuRepository.findById(2L)).thenReturn(Optional.of(pneu));

        assertThrows(VeiculoPneuException.class, () -> veiculoPneuService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveLancarExcecaoSeJaExistePneuNaPosicao() {
        InserirPneuVeiculoRequest request = new InserirPneuVeiculoRequest(1L, 2L, 2);
        Veiculo veiculo = new Veiculo();
        veiculo.setQuantidadeDePneus(4);
        Pneu pneu = new Pneu();

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(pneuRepository.findById(2L)).thenReturn(Optional.of(pneu));
        when(veiculoPneuRepository.findByPosicao(2)).thenReturn(Optional.of(new VeiculoPneu()));
        when(veiculoPneuRepository.findByVeiculoIdAndPosicao(1L, 2)).thenReturn(Optional.of(new VeiculoPneu()));

        assertThrows(VeiculoPneuException.class, () -> veiculoPneuService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveCadastraPneuNoVeiculoComSucesso() {
        InserirPneuVeiculoRequest request = new InserirPneuVeiculoRequest(1L, 2L, 2);
        Veiculo veiculo = new Veiculo();
        veiculo.setQuantidadeDePneus(4);
        Pneu pneu = new Pneu();

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(pneuRepository.findById(2L)).thenReturn(Optional.of(pneu));
        when(veiculoPneuRepository.findByPosicao(2)).thenReturn(Optional.of(new VeiculoPneu()));
        when(veiculoPneuRepository.findByVeiculoIdAndPosicao(1L, 2)).thenReturn(Optional.empty());

        when(veiculoMapper.toResponseFromInsertPneuEmVeiculo(veiculo)).thenReturn(mock(ResponseVehicle.class)); // ou mock do response
        when(pneuMapper.toInsertResponsePneuOnVeiculoFromEntity(pneu)).thenReturn(mock(PneuResponse.class));

        InserirPneuNoVeiculoValidandoPosicaoResponse response = veiculoPneuService.inserirPneuEmVeiculoComPosicao(request);

        assertEquals(2, response.getPosicao());
        assertNotNull(response.getPneu());
        assertNotNull(response.getVeiculo());

        verify(veiculoPneuRepository).save(any(VeiculoPneu.class));
    }

    @Test
    void deveLancarExcecaoAoRemoverSeNaoExisteVinculo() {
        RemoverPneuDoVeiculoRequest request = new RemoverPneuDoVeiculoRequest(1L, 2L);

        when(veiculoPneuRepository.findByVeiculoIdAndPneuId(1L, 2L)).thenReturn(Optional.empty());

        assertThrows(VeiculoPneuException.class, () -> veiculoPneuService.removerPneuDoVeiculo(request));
    }

    @Test
    void deveRemoverVinculoComSucesso() {
        RemoverPneuDoVeiculoRequest request = new RemoverPneuDoVeiculoRequest(1L, 2L);

        when(veiculoPneuRepository.findByVeiculoIdAndPneuId(1L, 2L)).thenReturn(Optional.of(new VeiculoPneu()));

        assertDoesNotThrow(() -> veiculoPneuService.removerPneuDoVeiculo(request));

        verify(veiculoPneuRepository).deleteByVeiculoIdAndPneuId(1L, 2L);
    }
}
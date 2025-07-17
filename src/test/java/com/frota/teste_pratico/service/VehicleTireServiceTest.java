package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.pneu.PneuResponse;
import com.frota.teste_pratico.dto.veiculo.ResponseVehicle;
import com.frota.teste_pratico.dto.veiculo_pneu.*;
import com.frota.teste_pratico.mapper.TireMapper;
import com.frota.teste_pratico.mapper.VehicleMapper;
import com.frota.teste_pratico.model.entities.Tire;
import com.frota.teste_pratico.model.entities.Vehicle;
import com.frota.teste_pratico.model.entities.VehicleTire;
import com.frota.teste_pratico.model.exceptions.VehicleTireException;
import com.frota.teste_pratico.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleTireServiceTest {

    @InjectMocks
    private VehicleTireService vehicleTireService;

    @Mock private VehicleMapper vehicleMapper;
    @Mock private TireMapper tireMapper;
    @Mock private VehicleTireRepository vehicleTireRepository;
    @Mock private VehicleRepository vehicleRepository;
    @Mock private TireRepository tireRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void deveLancarExcecaoQuandoVeiculoNaoExiste() {
        InsertTireOnVehicleRequest request = new InsertTireOnVehicleRequest(1L, 1L, 1);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VehicleTireException.class, () -> vehicleTireService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveLancarExcecaoQuandoPneuNaoExiste() {
        InsertTireOnVehicleRequest request = new InsertTireOnVehicleRequest(1L, 2L, 1);
        Vehicle vehicle = new Vehicle();
        vehicle.setTireQuantity(4);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(tireRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(VehicleTireException.class, () -> vehicleTireService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveLancarExcecaoSePosicaoMaiorQueQuantidadeDePneus() {
        InsertTireOnVehicleRequest request = new InsertTireOnVehicleRequest(1L, 2L, 5);
        Vehicle vehicle = new Vehicle();
        vehicle.setTireQuantity(4);
        Tire tire = new Tire();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(tireRepository.findById(2L)).thenReturn(Optional.of(tire));

        assertThrows(VehicleTireException.class, () -> vehicleTireService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveLancarExcecaoSeJaExistePneuNaPosicao() {
        InsertTireOnVehicleRequest request = new InsertTireOnVehicleRequest(1L, 2L, 2);
        Vehicle vehicle = new Vehicle();
        vehicle.setTireQuantity(4);
        Tire tire = new Tire();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(tireRepository.findById(2L)).thenReturn(Optional.of(tire));
        when(vehicleTireRepository.findByPosicao(2)).thenReturn(Optional.of(new VehicleTire()));
        when(vehicleTireRepository.findByVeiculoIdAndPosicao(1L, 2)).thenReturn(Optional.of(new VehicleTire()));

        assertThrows(VehicleTireException.class, () -> vehicleTireService.inserirPneuEmVeiculoComPosicao(request));
    }

    @Test
    void deveCadastraPneuNoVeiculoComSucesso() {
        InsertTireOnVehicleRequest request = new InsertTireOnVehicleRequest(1L, 2L, 2);
        Vehicle vehicle = new Vehicle();
        vehicle.setTireQuantity(4);
        Tire tire = new Tire();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(tireRepository.findById(2L)).thenReturn(Optional.of(tire));
        when(vehicleTireRepository.findByPosicao(2)).thenReturn(Optional.of(new VehicleTire()));
        when(vehicleTireRepository.findByVeiculoIdAndPosicao(1L, 2)).thenReturn(Optional.empty());

        when(vehicleMapper.toResponseFromInsertPneuEmVeiculo(vehicle)).thenReturn(mock(ResponseVehicle.class)); // ou mock do response
        when(tireMapper.toInsertResponsePneuOnVeiculoFromEntity(tire)).thenReturn(mock(PneuResponse.class));

        InsertTireOnVehicleValidatingPositionResponse response = vehicleTireService.inserirPneuEmVeiculoComPosicao(request);

        assertEquals(2, response.getPosicao());
        assertNotNull(response.getPneu());
        assertNotNull(response.getVeiculo());

        verify(vehicleTireRepository).save(any(VehicleTire.class));
    }

    @Test
    void deveLancarExcecaoAoRemoverSeNaoExisteVinculo() {
        removeTireFromVehicleRequest request = new removeTireFromVehicleRequest(1L, 2L);

        when(vehicleTireRepository.findByVeiculoIdAndPneuId(1L, 2L)).thenReturn(Optional.empty());

        assertThrows(VehicleTireException.class, () -> vehicleTireService.removerPneuDoVeiculo(request));
    }

    @Test
    void deveRemoverVinculoComSucesso() {
        removeTireFromVehicleRequest request = new removeTireFromVehicleRequest(1L, 2L);

        when(vehicleTireRepository.findByVeiculoIdAndPneuId(1L, 2L)).thenReturn(Optional.of(new VehicleTire()));

        assertDoesNotThrow(() -> vehicleTireService.removerPneuDoVeiculo(request));

        verify(vehicleTireRepository).deleteByVeiculoIdAndPneuId(1L, 2L);
    }
}
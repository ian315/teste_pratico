package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo.*;
import com.frota.teste_pratico.mapper.VehicleMapper;
import com.frota.teste_pratico.model.entities.Vehicle;
import com.frota.teste_pratico.model.enums.VehicleStatusEnum;
import com.frota.teste_pratico.model.exceptions.VehicleException;
import com.frota.teste_pratico.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarVeiculoComSucesso() {
        InsertVehicleRequest request = new InsertVehicleRequest();
        request.setPlate("ABC1234");

        Vehicle vehicleEntity = new Vehicle();
        InsertVehicleResponse response = new InsertVehicleResponse();

        when(vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.empty());
        when(vehicleMapper.toEntityFromInsertRequest(request)).thenReturn(vehicleEntity);
        when(vehicleRepository.save(vehicleEntity)).thenReturn(vehicleEntity);
        when(vehicleMapper.toResponseDtoFromEntity(vehicleEntity)).thenReturn(response);

        InsertVehicleResponse result = vehicleService.cadastrarVeiculo(request);

        assertEquals(response, result);
        verify(vehicleRepository).save(vehicleEntity);
    }

    @Test
    void deveLancarExcecaoSePlacaJaExistente() {
        InsertVehicleRequest request = new InsertVehicleRequest();
        request.setPlate("XYZ9876");

        when(vehicleRepository.findByPlate("XYZ9876")).thenReturn(Optional.of(new Vehicle()));

        assertThrows(VehicleException.class, () -> {
            vehicleService.cadastrarVeiculo(request);
        });

        verify(vehicleRepository, never()).save(any());
    }

    @Test
    void deveRetornarListaDeVeiculos() {
        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle();

        SearchAllVehiclesResponse r1 = new SearchAllVehiclesResponse();
        SearchAllVehiclesResponse r2 = new SearchAllVehiclesResponse();

        when(vehicleRepository.findAll()).thenReturn(List.of(v1, v2));
        when(vehicleMapper.toFindAllResponseDtoFromEntity(v1)).thenReturn(r1);
        when(vehicleMapper.toFindAllResponseDtoFromEntity(v2)).thenReturn(r2);

        List<SearchAllVehiclesResponse> result = vehicleService.buscarTodosVeiculos();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(r1, r2)));
    }

    @Test
    void deveRetornarVeiculoComPneusQuandoIdExiste() {
        Long id = 1L;
        Vehicle vehicle = new Vehicle();
        SearchVehicleByPlateWithTiresResponse response = new SearchVehicleByPlateWithTiresResponse();

        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toFindVeiculoWithPneusResponseFromEntity(vehicle)).thenReturn(response);

        SearchVehicleByPlateWithTiresResponse result = vehicleService.buscarVeiculoPorId(id);

        assertEquals(response, result);
    }

    @Test
    void deveMostrarVeiculoComListaDePneusVaziaSeNaoTiverPneuCadastrado() {
        Long id = 99L;
        Vehicle vehicle = new Vehicle(
                99L, "PlacaTeste","MarcaTeste", 1000, VehicleStatusEnum.ATIVO, 4, Collections.EMPTY_LIST);

        SearchVehicleByPlateWithTiresResponse dto = new SearchVehicleByPlateWithTiresResponse(
                "PlacaTeste","MarcaTeste", 1000, VehicleStatusEnum.ATIVO, 4, Collections.EMPTY_LIST);

        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toFindVeiculoWithPneusResponseFromEntity(vehicle)).thenReturn(dto);

        SearchVehicleByPlateWithTiresResponse response = vehicleService.buscarVeiculoPorId(id);

        assertNotNull(response);
        assertTrue(response.getTireList().isEmpty());
    }
}
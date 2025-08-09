package com.motta.service;

import com.motta.dto.fuel.FuelRequest;
import com.motta.dto.fuel.FuelResponse;
import com.motta.exception.FuelNotFoundException;
import com.motta.modal.Fuel;
import com.motta.repository.FuelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuelServiceTest {

    @Mock
    private FuelRepository fuelRepository;

    @InjectMocks
    private FuelService fuelService;

    @DisplayName("Should return successfully create a fuel")
    @Test
    void testCreateFuelWithSuccess() {
        FuelRequest fuelRequest = new FuelRequest("Gasoline", new BigDecimal("3.50"));

        FuelResponse actual = this.fuelService.createFuel(fuelRequest);

        verify(fuelRepository, times(1)).save(any(Fuel.class));
        assertInstanceOf(FuelResponse.class, actual);
        assertEquals(actual.name(), fuelRequest.name());
        assertEquals(actual.pricePerLiter(), fuelRequest.pricePerLiter());
    }

    @DisplayName("Should return successfully a list of fuels")
    @Test
    void testGetAllFuelsWithSuccess() {

        when(fuelRepository.findAll()).thenReturn(
                List.of(
                        new Fuel(UUID.randomUUID().toString(), "Gasoline", new BigDecimal("3.50"), null)
                )
        );

        List<FuelResponse> actual = this.fuelService.getAllFuels();

        verify(fuelRepository, times(1)).findAll();
        assertEquals(1, actual.size());
        assertEquals("Gasoline", actual.getFirst().name());
        assertEquals(new BigDecimal("3.50"), actual.getFirst().pricePerLiter());
    }

    @DisplayName("Should return successfully update a fuel")
    @Test
    void testUpdateFuelWithSuccess() {

        Fuel fuel = new Fuel(UUID.randomUUID().toString(), "Gasoline", new BigDecimal("3.50"), null);

        when(fuelRepository.findById(anyString())).thenReturn(Optional.of(fuel));

        FuelRequest fuelRequest = new FuelRequest("Diesel", new BigDecimal("4.50"));
        FuelResponse actual = this.fuelService.updateFuel(fuelRequest, fuel.getId());

        verify(fuelRepository, times(1)).findById(anyString());
        assertInstanceOf(FuelResponse.class, actual);
        assertEquals(actual.name(), fuelRequest.name());
        assertEquals(actual.pricePerLiter(), fuelRequest.pricePerLiter());

    }

    @DisplayName("Should return exception when try update a fuel that not exists")
    @Test
    void testUpdateFuelWithException() {

        when(fuelRepository.findById(anyString())).thenReturn(Optional.empty());

        String id = UUID.randomUUID().toString();

        FuelRequest fuelRequest = new FuelRequest("Diesel", new BigDecimal("4.50"));

        FuelNotFoundException exception = assertThrows(FuelNotFoundException.class, () ->
                fuelService.updateFuel(fuelRequest, id));

        assertEquals("Fuel not found with id: " + id, exception.getMessage());

    }

    @DisplayName("Should return successfully delete a fuel")
    @Test
    void testDeleteFuelWithSuccess() {

        String fuelId = UUID.randomUUID().toString();

        Fuel fuel = new Fuel(fuelId, "Gasoline", new BigDecimal("3.50"), null);

        when(fuelRepository.findById(anyString())).thenReturn(Optional.of(fuel));

        assertDoesNotThrow(() -> fuelService.deleteFuel(fuel.getId()));

        verify(fuelRepository, times(1)).findById(fuelId);
        verify(fuelRepository, times(1)).delete(fuel);

    }

    @DisplayName("Should return exception when try delete a fuel that not exists")
    @Test
    void testDeleteFuelWithException() {

        when(fuelRepository.findById(anyString())).thenReturn(Optional.empty());

        String id = UUID.randomUUID().toString();

        FuelNotFoundException exception = assertThrows(FuelNotFoundException.class, () ->
                fuelService.deleteFuel(id));

        assertEquals("Fuel not found with id: " + id, exception.getMessage());

    }

}

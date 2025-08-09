package com.motta.service;

import com.motta.dto.fuel.FuelResponse;
import com.motta.dto.fuelpump.FuelPumpRequest;
import com.motta.dto.fuelpump.FuelPumpResponse;
import com.motta.exception.FuelNotFoundException;
import com.motta.exception.FuelPumpNotFoundException;
import com.motta.modal.Fuel;
import com.motta.modal.FuelPump;
import com.motta.repository.FuelPumpRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuelPumpServiceTest {

    @Mock
    private FuelService fuelService;

    @Mock
    private FuelPumpRepository fuelPumpRepository;

    @InjectMocks
    private FuelPumpService fuelPumpService;

    @DisplayName("Should return success when create a fuel pump")
    @Test
    void testCreateFuelPumpWithSuccess() {

        String fuelId = UUID.randomUUID().toString();

        Fuel fuel = new Fuel(fuelId, "Gasoline", new BigDecimal("3.50"), null);
        FuelPumpRequest fuelPumpRequest = new FuelPumpRequest("Fuel Pump", fuelId);

        when(fuelService.findById(fuelId)).thenReturn(fuel);

        FuelPumpResponse actual = this.fuelPumpService.createFuelPump(fuelPumpRequest);

        verify(fuelService, times(1)).findById(fuelId);
        verify(fuelPumpRepository, times(1)).save(any(FuelPump.class));

        assertInstanceOf(FuelPumpResponse.class, actual);

    }

    @DisplayName("Should return a list of fuel pumps")
    @Test
    void testGetAllFuelPumpsWithSuccess() {

        Fuel fuel = new Fuel(UUID.randomUUID().toString(), "Gasoline", new BigDecimal("3.50"), null);

        when(this.fuelPumpRepository.findAll()).thenReturn(
                List.of(
                        new FuelPump(UUID.randomUUID().toString(), "Fuel Pump", fuel, null)
                )
        );

        List<FuelPumpResponse> actual = this.fuelPumpService.getAllFuelPumps();

        verify(fuelPumpRepository, times(1)).findAll();
        assertEquals(1, actual.size());
        assertEquals("Fuel Pump", actual.getFirst().name());
        assertEquals(new FuelResponse(fuel), actual.getFirst().fuel());

    }

    @DisplayName("Should return success when update a fuel pump")
    @Test
    void testUpdateFuelPumpWithSuccess() {

        String fuelId = UUID.randomUUID().toString();
        String fuelPumpId = UUID.randomUUID().toString();

        Fuel fuel = new Fuel(fuelId, "Gasoline", new BigDecimal("3.50"), null);
        FuelPump fuelPump = new FuelPump(fuelPumpId, "Fuel Pump", fuel, null);
        FuelPumpRequest fuelPumpRequest = new FuelPumpRequest("Fuel Pump Updated", fuelId);

        when(fuelService.findById(fuelId)).thenReturn(fuel);
        when(fuelPumpRepository.findById(fuelPumpId)).thenReturn(Optional.of(fuelPump));

        FuelPumpResponse actual = this.fuelPumpService.updateFuelPump(fuelPumpRequest, fuelPumpId);

        verify(fuelService, times(1)).findById(fuelId);
        verify(fuelPumpRepository, times(1)).findById(fuelPumpId);

        assertInstanceOf(FuelPumpResponse.class, actual);
        assertEquals("Fuel Pump Updated", actual.name());

    }

    @DisplayName("Should return exception when try update a fuel pump that not exists")
    @Test
    void testUpdateFuelPumpWithException() {

        String fuelPumpid = UUID.randomUUID().toString();

        FuelPumpRequest fuelPumpRequest = new FuelPumpRequest("Fuel Pump Updated", fuelPumpid);

        when(this.fuelPumpRepository.findById(fuelPumpid)).thenReturn(Optional.empty());

        FuelPumpNotFoundException exception = assertThrows(FuelPumpNotFoundException.class,
                () -> this.fuelPumpService.updateFuelPump(fuelPumpRequest, fuelPumpid));

        verify(fuelPumpRepository, times(1)).findById(fuelPumpid);
        assertEquals("Fuel Pump not found with id: " + fuelPumpid, exception.getMessage());

    }

    @DisplayName("Should return successfully delete a fuel pump")
    @Test
    void testDeleteFuelWithSuccess() {

        String fuelPumpId = UUID.randomUUID().toString();

        Fuel fuel = new Fuel(UUID.randomUUID().toString(), "Gasoline", new BigDecimal("3.50"), null);

        FuelPump fuelPump = new FuelPump(fuelPumpId, "Fuel Pump", fuel, null);

        when(fuelPumpRepository.findById(fuelPumpId)).thenReturn(Optional.of(fuelPump));

        assertDoesNotThrow(() -> fuelPumpService.deleteFuelPump(fuelPumpId));
        verify(fuelPumpRepository, times(1)).findById(fuelPumpId);
        verify(fuelPumpRepository, times(1)).delete(fuelPump);

    }

    @DisplayName("Should return exception when try delete a fuel pump that not exists")
    @Test
    void testDeleteFuelPumpWithException() {

        String fuelPumpId = UUID.randomUUID().toString();

        when(this.fuelPumpRepository.findById(fuelPumpId)).thenReturn(Optional.empty());

        FuelPumpNotFoundException exception = assertThrows(FuelPumpNotFoundException.class,
                () -> this.fuelPumpService.deleteFuelPump(fuelPumpId));

        verify(fuelPumpRepository, times(1)).findById(fuelPumpId);
        assertEquals("Fuel Pump not found with id: " + fuelPumpId, exception.getMessage());

    }

}

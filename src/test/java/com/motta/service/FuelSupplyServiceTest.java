package com.motta.service;

import com.motta.dto.fuelsupply.FuelSupplyRequest;
import com.motta.dto.fuelsupply.FuelSupplyResponse;
import com.motta.modal.Fuel;
import com.motta.modal.FuelPump;
import com.motta.modal.FuelSupply;
import com.motta.repository.FuelSupplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuelSupplyServiceTest {

    @Mock
    private FuelPumpService fuelPumpService;

    @Mock
    private FuelSupplyRepository fuelSupplyRepository;

    @InjectMocks
    private FuelSupplyService fuelSupplyService;

    @DisplayName("Should return successfully create a fuel supply")
    @Test
    void testCreateFuelSupplyWithSuccess() {
        String fuelPumpId = UUID.randomUUID().toString();
        FuelSupplyRequest fuelSupplyRequest = new FuelSupplyRequest(10L, fuelPumpId);

        Fuel fuel = new Fuel(UUID.randomUUID().toString(), "Gasoline", new BigDecimal("3.50"), null);
        FuelPump fuelPump = new FuelPump(UUID.randomUUID().toString(), "Fuel Pump 1",  fuel, null);

        when(fuelPumpService.findById(fuelPumpId)).thenReturn(fuelPump);

        FuelSupplyResponse actual = this.fuelSupplyService.createFuelSupply(fuelSupplyRequest);

        BigDecimal expectedTotalPrice = fuel.getPricePerLiter().multiply(new BigDecimal(fuelSupplyRequest.liters()));

        verify(fuelSupplyRepository, times(1)).save(any(FuelSupply.class));
        assertInstanceOf(FuelSupplyResponse.class, actual);
        assertEquals(fuelSupplyRequest.liters(), actual.liters());
        assertEquals(expectedTotalPrice, actual.totalPrice());
    }

    @DisplayName("Should return successfully get all fuel supplies")
    @Test
    void testGetAllFuelSuppliesWithSuccess() {

        Fuel fuel = new Fuel(UUID.randomUUID().toString(), "Gasoline", new BigDecimal("3.50"), null);
        FuelPump fuelPump = new FuelPump(UUID.randomUUID().toString(), "Fuel Pump 1",  fuel, null);

        List<FuelSupply> fuelSupplies = List.of(
                new FuelSupply(UUID.randomUUID().toString(), LocalDateTime.now(), 10L, new BigDecimal("35.00"), fuelPump)
        );

        when(fuelSupplyRepository.findAll()).thenReturn(fuelSupplies);

        List<FuelSupplyResponse> actual = this.fuelSupplyService.getAllFuelSupplies();

        verify(fuelSupplyRepository, times(1)).findAll();

        assertEquals(1, actual.size());
        assertEquals(new BigDecimal("35.00"), actual.getFirst().totalPrice());
        assertEquals(fuelPump.getId(), actual.getFirst().fuelPump().id());

    }

}

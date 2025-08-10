package com.motta.dto.fuelsupply;

import com.motta.dto.fuelpump.FuelPumpResponse;
import com.motta.modal.FuelSupply;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FuelSupplyResponse(

        String id,
        LocalDateTime date,
        Long liters,
        BigDecimal totalPrice,
        FuelPumpResponse fuelPump

) {

    public FuelSupplyResponse(FuelSupply fuelSupply){
        this(
                fuelSupply.getId(),
                fuelSupply.getDate(),
                fuelSupply.getLiters(),
                fuelSupply.getTotalPrice(),
                new FuelPumpResponse(fuelSupply.getFuelPump())
        );
    }

}

package com.motta.dto.fuelpump;

import com.motta.dto.fuel.FuelResponse;
import com.motta.modal.FuelPump;

public record FuelPumpResponse(

        String id,
        String name,
        FuelResponse fuel

) {

    public FuelPumpResponse(FuelPump fuelPump) {
        this(
                fuelPump.getId(),
                fuelPump.getName(),
                new FuelResponse(fuelPump.getFuel())
        );
    }

}

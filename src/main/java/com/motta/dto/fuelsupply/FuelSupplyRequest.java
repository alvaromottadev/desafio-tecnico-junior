package com.motta.dto.fuelsupply;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record FuelSupplyRequest(

        @Min(value = 1, message = "Liters must be at least 1")
        Long liters,

        @NotBlank(message = "Fuel pump ID is required")
        String fuelPumpId

) {
}

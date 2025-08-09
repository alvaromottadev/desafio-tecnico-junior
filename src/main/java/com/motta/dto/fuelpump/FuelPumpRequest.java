package com.motta.dto.fuelpump;

import jakarta.validation.constraints.NotBlank;

public record FuelPumpRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Fuel ID is required")
        String fuelId

) {
}

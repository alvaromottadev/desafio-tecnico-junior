package com.motta.dto.fuel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FuelRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Price per liter is required")
        BigDecimal pricePerLiter

) {
}

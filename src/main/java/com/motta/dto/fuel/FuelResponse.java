package com.motta.dto.fuel;

import com.motta.modal.Fuel;

import java.math.BigDecimal;

public record FuelResponse(

        String id,
        String name,
        BigDecimal pricePerLiter


) {

    public FuelResponse(Fuel fuel){
        this(
                fuel.getId(),
                fuel.getName(),
                fuel.getPricePerLiter()
        );
    }

}

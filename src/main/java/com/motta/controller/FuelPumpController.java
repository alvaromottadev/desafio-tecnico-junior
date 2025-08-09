package com.motta.controller;

import com.motta.dto.fuelpump.FuelPumpRequest;
import com.motta.dto.fuelpump.FuelPumpResponse;
import com.motta.service.FuelPumpService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fuelpumps")
public class FuelPumpController {

    private final FuelPumpService fuelPumpService;

    public FuelPumpController(FuelPumpService fuelPumpService) {
        this.fuelPumpService = fuelPumpService;
    }

    @PostMapping
    public ResponseEntity<FuelPumpResponse> createFuelPump(@Validated @RequestBody FuelPumpRequest fuelPumpRequest) {
        FuelPumpResponse response = fuelPumpService.createFuelPump(fuelPumpRequest);
        return ResponseEntity.status(201).body(response);
    }

}

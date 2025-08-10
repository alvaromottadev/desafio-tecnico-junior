package com.motta.controller;

import com.motta.dto.fuelpump.FuelPumpRequest;
import com.motta.dto.fuelpump.FuelPumpResponse;
import com.motta.service.FuelPumpService;
import com.motta.swagger.FuelPumpSwagger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuelpumps")
public class FuelPumpController implements FuelPumpSwagger {

    private final FuelPumpService fuelPumpService;

    public FuelPumpController(FuelPumpService fuelPumpService) {
        this.fuelPumpService = fuelPumpService;
    }

    @PostMapping
    public ResponseEntity<FuelPumpResponse> createFuelPump(@Validated @RequestBody FuelPumpRequest fuelPumpRequest) {
        FuelPumpResponse response = fuelPumpService.createFuelPump(fuelPumpRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FuelPumpResponse>> getAllFuelPumps() {
        List<FuelPumpResponse> fuelPumps = fuelPumpService.getAllFuelPumps();
        return ResponseEntity.ok(fuelPumps);
    }

    @PutMapping("/{fuelPumpId}")
    public ResponseEntity<FuelPumpResponse> updateFuelPump(@Validated @RequestBody FuelPumpRequest fuelPumpRequest, @PathVariable String fuelPumpId) {
        FuelPumpResponse response = fuelPumpService.updateFuelPump(fuelPumpRequest, fuelPumpId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{fuelPumpId}")
    public ResponseEntity<Void> deleteFuelPump(@PathVariable String fuelPumpId) {
        fuelPumpService.deleteFuelPump(fuelPumpId);
        return ResponseEntity.noContent().build();
    }

}

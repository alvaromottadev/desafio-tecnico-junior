package com.motta.controller;

import com.motta.dto.FuelRequest;
import com.motta.dto.FuelResponse;
import com.motta.repository.FuelRepository;
import com.motta.service.FuelService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuels")
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping
    public ResponseEntity<FuelResponse> createFuel(@Validated @RequestBody FuelRequest fuelRequest) {
        FuelResponse response = fuelService.createFuel(fuelRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FuelResponse>> getAllFuels() {
        List<FuelResponse> fuels = fuelService.getAllFuels();
        return ResponseEntity.ok(fuels);
    }

}

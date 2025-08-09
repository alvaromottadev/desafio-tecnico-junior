package com.motta.controller;

import com.motta.dto.FuelRequest;
import com.motta.dto.FuelResponse;
import com.motta.service.FuelService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fuels")
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping
    public ResponseEntity<?> createFuel(@Validated @RequestBody FuelRequest fuelRequest) {
        FuelResponse response = fuelService.createFuel(fuelRequest);
        return ResponseEntity.ok(response);
    }

}

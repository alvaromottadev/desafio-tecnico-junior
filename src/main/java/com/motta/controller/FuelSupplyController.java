package com.motta.controller;

import com.motta.dto.fuelsupply.FuelSupplyRequest;
import com.motta.dto.fuelsupply.FuelSupplyResponse;
import com.motta.service.FuelSupplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuelsupplies")
public class FuelSupplyController {

    private final FuelSupplyService fuelSupplyService;

    public FuelSupplyController(FuelSupplyService fuelSupplyService) {
        this.fuelSupplyService = fuelSupplyService;
    }

    @PostMapping
    public ResponseEntity<FuelSupplyResponse> createFuelSupply(@Validated @RequestBody FuelSupplyRequest fuelSupplyRequest) {
        FuelSupplyResponse response = fuelSupplyService.createFuelSupply(fuelSupplyRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FuelSupplyResponse>> getAllFuelSupply() {
        List<FuelSupplyResponse> fuelSupplyResponses = fuelSupplyService.getAllFuelSupplies();
        return ResponseEntity.ok(fuelSupplyResponses);
    }

}

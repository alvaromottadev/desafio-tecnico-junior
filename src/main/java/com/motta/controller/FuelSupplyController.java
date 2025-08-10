package com.motta.controller;

import com.motta.dto.fuelsupply.FuelSupplyRequest;
import com.motta.dto.fuelsupply.FuelSupplyResponse;
import com.motta.service.FuelSupplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fuelsupplies")
public class FuelSupplyController {

    private final FuelSupplyService fuelSupplyService;

    public FuelSupplyController(FuelSupplyService fuelSupplyService) {
        this.fuelSupplyService = fuelSupplyService;
    }

    @PostMapping
    public ResponseEntity<?> createFuelSupply(@Validated @RequestBody FuelSupplyRequest fuelSupplyRequest) {
        FuelSupplyResponse response = fuelSupplyService.createFuelSupply(fuelSupplyRequest);
        return ResponseEntity.status(201).body(response);
    }

}

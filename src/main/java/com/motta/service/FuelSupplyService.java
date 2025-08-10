package com.motta.service;

import com.motta.dto.fuelsupply.FuelSupplyRequest;
import com.motta.dto.fuelsupply.FuelSupplyResponse;
import com.motta.modal.FuelPump;
import com.motta.modal.FuelSupply;
import com.motta.repository.FuelSupplyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuelSupplyService {

    private final FuelPumpService fuelPumpService;
    private final FuelSupplyRepository fuelSupplyRepository;

    public FuelSupplyService(FuelPumpService fuelPumpService, FuelSupplyRepository fuelSupplyRepository) {
        this.fuelPumpService = fuelPumpService;
        this.fuelSupplyRepository = fuelSupplyRepository;
    }

    @Transactional
    public FuelSupplyResponse createFuelSupply(FuelSupplyRequest fuelSupplyRequest){
        FuelPump fuelPump = fuelPumpService.findById(fuelSupplyRequest.fuelPumpId());

        BigDecimal totalPrice = calculateTotalPrice(fuelSupplyRequest.liters(), fuelPump);
        FuelSupply fuelSupply = new FuelSupply(fuelSupplyRequest, fuelPump, totalPrice);

        fuelSupplyRepository.save(fuelSupply);
        return new FuelSupplyResponse(fuelSupply);
    }

    public List<FuelSupplyResponse> getAllFuelSupplies() {
        return fuelSupplyRepository.findAll().stream()
                .map(FuelSupplyResponse::new)
                .toList();
    }

    private BigDecimal calculateTotalPrice(Long liters, FuelPump fuelPump){
        BigDecimal pricePerLiter = fuelPump.getFuel().getPricePerLiter();
        return pricePerLiter.multiply(new BigDecimal(liters));
    }

}

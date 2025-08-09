package com.motta.service;

import com.motta.dto.fuelpump.FuelPumpRequest;
import com.motta.dto.fuelpump.FuelPumpResponse;
import com.motta.modal.Fuel;
import com.motta.modal.FuelPump;
import com.motta.repository.FuelPumpRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FuelPumpService {

    private final FuelService fuelService;
    private final FuelPumpRepository fuelPumpRepository;

    public FuelPumpService(FuelService fuelService, FuelPumpRepository fuelPumpRepository) {
        this.fuelService = fuelService;
        this.fuelPumpRepository = fuelPumpRepository;
    }

    @Transactional
    public FuelPumpResponse createFuelPump(FuelPumpRequest fuelPumpRequest){
        Fuel fuel = fuelService.findById(fuelPumpRequest.fuelId());
        FuelPump fuelPump = new FuelPump(fuelPumpRequest, fuel);
        fuelPumpRepository.save(fuelPump);
        return new FuelPumpResponse(fuelPump);
    }

}

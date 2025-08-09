package com.motta.service;

import com.motta.dto.FuelRequest;
import com.motta.dto.FuelResponse;
import com.motta.modal.Fuel;
import com.motta.repository.FuelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {

    private final FuelRepository fuelRepository;

    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @Transactional
    public FuelResponse createFuel(FuelRequest fuelRequest){
        Fuel fuel = new Fuel(fuelRequest);
        fuelRepository.save(fuel);
        return new FuelResponse(fuel);
    }

    public List<FuelResponse> getAllFuels() {
        return fuelRepository.findAll().stream().map(FuelResponse::new).toList();
    }

}

package com.motta.service;

import com.motta.dto.fuel.FuelRequest;
import com.motta.dto.fuel.FuelResponse;
import com.motta.exception.FuelNotFoundException;
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

    @Transactional
    public FuelResponse updateFuel(FuelRequest fuelRequest, String fuelId){
        Fuel fuel = this.findById(fuelId);
        fuel.update(fuelRequest);
        return new FuelResponse(fuel);
    }

    @Transactional
    public void deleteFuel(String fuelId){
        Fuel fuel = this.findById(fuelId);
        fuelRepository.delete(fuel);
    }

    private Fuel findById(String fuelId){
        return fuelRepository.findById(fuelId)
                .orElseThrow(() -> new FuelNotFoundException("Fuel not found with id: " + fuelId));
    }


}

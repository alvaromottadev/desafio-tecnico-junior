package com.motta.repository;

import com.motta.modal.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository<Fuel, String> {
}

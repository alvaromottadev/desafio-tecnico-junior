package com.motta.modal;

import com.motta.dto.fuelpump.FuelPumpRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "fuel_pumps")
public class FuelPump {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Fuel fuel;

    @OneToMany(mappedBy = "fuelPump")
    private List<FuelSupply> fuelSupplies;

    public void update(FuelPumpRequest fuelPumpRequest, Fuel fuel) {
        this.name = fuelPumpRequest.name();
        this.fuel = fuel;
    }

    public FuelPump(FuelPumpRequest fuelPumpRequest, Fuel fuel){
        this.name = fuelPumpRequest.name();
        this.fuel = fuel;
    }

}

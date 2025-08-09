package com.motta.modal;

import com.motta.dto.fuel.FuelRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "fuels")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal pricePerLiter;

    @OneToMany(mappedBy = "fuel")
    private List<FuelPump> fuelPumps;

    public void update(FuelRequest fuelRequest){
        this.name = fuelRequest.name();
        this.pricePerLiter = fuelRequest.pricePerLiter();
    }

    public Fuel(FuelRequest fuelRequest){
        this.name = fuelRequest.name();
        this.pricePerLiter = fuelRequest.pricePerLiter();
    }

}

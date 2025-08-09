package com.motta.modal;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

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

}

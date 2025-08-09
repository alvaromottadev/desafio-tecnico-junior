package com.motta.modal;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel_pumps")
public class FuelPump {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Fuel fuel;

}

package com.motta.modal;

import com.motta.dto.fuelsupply.FuelSupplyRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "fuel_supplies")
public class FuelSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Long liters;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FuelPump fuelPump;

    public FuelSupply(FuelSupplyRequest fuelSupplyRequest, FuelPump fuelPump, BigDecimal totalPrice){
        this.date = LocalDateTime.now();
        this.liters = fuelSupplyRequest.liters();
        this.fuelPump = fuelPump;
        this.totalPrice = totalPrice;
    }

}

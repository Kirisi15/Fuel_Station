package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="fuel")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fuelId;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "addedFuel")
    private double addedFuel;

    @Column(name = "pumpedFuel")
    private double pumpedFuel;

    @OneToMany(mappedBy = "fuel", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToMany(mappedBy = "fuel")
    private List<FuelStation> fuelStations = new ArrayList<>();

    public Fuel(String fuelType, double addedFuel, double pumpedFuel, List<Transaction> transactions, List<FuelStation> fuelStations) {
        this.fuelType = fuelType;
        this.addedFuel = addedFuel;
        this.pumpedFuel = pumpedFuel;
        this.transactions = transactions;
        this.fuelStations = fuelStations;
    }

}
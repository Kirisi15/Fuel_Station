package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="fuel")
public class FuelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fuelId;

    @Column(name="fuel_type")
    private String fuelType;

    @Column(name = "addedFuel")
    private double addedFuel;

    @Column(name = "pumpedFuel")
    private double pumpedFuel;

    @OneToMany(mappedBy = "fuel", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();

    @ManyToMany(mappedBy = "fuel")
    private List<FuelStationEntity> fuelStations = new ArrayList<>();

    public FuelEntity(long fuelId, String fuelType, double addedFuel, double pumpedFuel) {
        this.fuelId = fuelId;
        this.fuelType = fuelType;
        this.addedFuel = addedFuel;
        this.pumpedFuel = pumpedFuel;
    }

    public FuelEntity() {
    }

    public long getFuelId() {
        return fuelId;
    }

    public void setFuelId(long fuelId) {
        this.fuelId = fuelId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getAddedFuel() {
        return addedFuel;
    }

    public void setAddedFuel(double addedFuel) {
        this.addedFuel = addedFuel;
    }

    public double getPumpedFuel() {
        return pumpedFuel;
    }

    public void setPumpedFuel(double pumpedFuel) {
        this.pumpedFuel = pumpedFuel;
    }
}

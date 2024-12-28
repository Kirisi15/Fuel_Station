package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="fuel")
public class FuelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fuelId;


    private int fuelId;
  
    @OneToMany(mappedBy = "fuel", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();

    @ManyToMany(mappedBy = "fuel")
    private List<FuelStationEntity> fuelStations = new ArrayList<>();

 

    @Column(name="fuel_type")
    private String fuelType;

    @Column(name="remain_fuel")
    private String remailFuel;

    public FuelEntity(int fuelId, String fuelType, String remailFuel) {
        this.fuelId= fuelId;
        this.fuelType = fuelType;
        this.remailFuel = remailFuel;
    }

    public FuelEntity() {
    }

    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int pumpId) {
        this.fuelId = pumpId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getRemailFuel() {
        return remailFuel;
    }

    public void setRemailFuel(String remailFuel) {
        this.remailFuel = remailFuel;
    }

    @Override
    public String toString() {
        return "FuelEntity{}";
    }
}

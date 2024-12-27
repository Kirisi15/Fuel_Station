package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="fuel")
public class FuelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int fuelId;
    @OneToMany(mappedBy = "fuel", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();

    @ManyToMany(mappedBy = "fuels")
    private Set<FuelStationEntity> fuelStations = new HashSet<>();

 

    @Column(name="fuel_type")
    private String fuelType;

    @Column(name="remain_fuel")
    private String remailFuel;

    public FuelEntity(int fuelId, String fuelType, String remailFuel) {
        this.fuelId= fuelId;
        this.fuelType = fuelType;
        this.remailFuel = remailFuel;
    }

    public int getPumpId() {
        return fuelId;
    }

    public void setPumpId(int pumpId) {
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

package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="fuel")
public class FuelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fuelId;

    @Column(name="fuel_type")
    private String fuelType;

    @Column(name="remain_fuel")
    private String remailFuel;

    public FuelEntity(int pumpId, String fuelType, String remailFuel) {
        this.fuelId= pumpId;
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

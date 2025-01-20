package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FuelStation_Fuel")
public class FuelStationFuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private FuelStationEntity fuelStation;

    @ManyToOne
    @JoinColumn(name = "fuelId")
    private FuelEntity fuel;

    @Column(name = "addedFuel")
    private double addedFuel;

    @Column(name = "pumpedFuel")
    private double pumpedFuel;

    public FuelStationFuel() {
    }

    public FuelStationFuel(FuelStationEntity fuelStation, FuelEntity fuel, double addedFuel, double pumpedFuel) {
        this.fuelStation = fuelStation;
        this.fuel = fuel;
        this.addedFuel = addedFuel;
        this.pumpedFuel = pumpedFuel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FuelStationEntity getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStationEntity fuelStation) {
        this.fuelStation = fuelStation;
    }

    public FuelEntity getFuel() {
        return fuel;
    }

    public void setFuel(FuelEntity fuel) {
        this.fuel = fuel;
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
    //Test to push the code
}


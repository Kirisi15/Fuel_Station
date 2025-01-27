package Fuel_Station.Fuel_Station.Entity;

import Fuel_Station.Fuel_Station.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="vehicle")
@Getter
@Setter
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VehicleId;


    private String VehicleNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private String FuelType;

    @OneToOne
    @JoinColumn(name = "fuel_limit_id")
    private FuelLimit fuelLimitId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Vehicle_FuelStation",
            joinColumns = @JoinColumn(name = "VehicleId"),
            inverseJoinColumns = @JoinColumn(name = "StationId")
    )
    private List<FuelStation> fuelStations = new ArrayList<>();


    public Long getVehicleId() {
        return VehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setVehicleId(Long vehicleId) {
        VehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }



    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public FuelLimit getFuelLimitId() {
        return fuelLimitId;
    }

    public void setFuelLimitId(FuelLimit fuelLimitId) {
        this.fuelLimitId = fuelLimitId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<FuelStation> getFuelStations() {
        return fuelStations;
    }

    public void setFuelStations(List<FuelStation> fuelStations) {
        this.fuelStations = fuelStations;
    }
}




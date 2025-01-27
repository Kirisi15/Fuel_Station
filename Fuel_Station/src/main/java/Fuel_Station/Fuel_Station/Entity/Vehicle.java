package Fuel_Station.Fuel_Station.Entity;

import Fuel_Station.Fuel_Station.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="vehicle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Vehicle(String vehicleNumber, VehicleType vehicleType, String fuelType, FuelLimit fuelLimitId, Customer customer) {
        VehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        FuelType = fuelType;
        this.fuelLimitId = fuelLimitId;
        this.customer = customer;
    }

    @ManyToMany
    @JoinTable(
            name = "Vehicle_FuelStation",
            joinColumns = @JoinColumn(name = "VehicleId"),
            inverseJoinColumns = @JoinColumn(name = "StationId")
    )
    private List<FuelStation> fuelStations = new ArrayList<>();




}




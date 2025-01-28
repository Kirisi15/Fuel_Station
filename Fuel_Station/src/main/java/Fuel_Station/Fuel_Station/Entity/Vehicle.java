package Fuel_Station.Fuel_Station.Entity;

import Fuel_Station.Fuel_Station.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String vehicleNumber;
    @Column
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private String fuelType;
    @OneToOne
    @JoinColumn(name = "fuel_limit_id")
    private FuelLimit fuelLimitId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
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

    public Vehicle(String vehicleNumber, VehicleType vehicleType, String fuelType, FuelLimit fuelLimitId, Customer customer) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.fuelLimitId = fuelLimitId;
        this.customer = customer;
    }
}




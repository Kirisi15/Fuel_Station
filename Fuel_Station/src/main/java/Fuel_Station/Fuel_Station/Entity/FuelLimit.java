package Fuel_Station.Fuel_Station.Entity;

import Fuel_Station.Fuel_Station.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "fuel_limit")
public class FuelLimit {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


    @Column
    private int fuelLimit;

}

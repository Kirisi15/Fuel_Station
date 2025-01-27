package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "vehicle_fuel_quota")
public class VehicleFuelQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "vehicle_Id")
    @OneToOne
    private Vehicle vehicle;

    @JoinColumn(name = "time_period")
    @OneToOne
    private TimePeriod timePeriod;

    @Column(name = "pumped_fuel")
    private int pumpedFuel;

}

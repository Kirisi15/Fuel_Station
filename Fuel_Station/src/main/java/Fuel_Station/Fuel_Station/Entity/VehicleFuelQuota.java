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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public int getPumpedFuel() {
        return pumpedFuel;
    }

    public void setPumpedFuel(int pumpedFuel) {
        this.pumpedFuel = pumpedFuel;
    }
}

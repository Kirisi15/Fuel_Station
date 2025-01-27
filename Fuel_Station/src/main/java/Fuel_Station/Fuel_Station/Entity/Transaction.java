package Fuel_Station.Fuel_Station.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "stationId")
    private FuelStation fuelStation;
    @ManyToOne
    @JoinColumn(name = "fuelId")
    private Fuel fuel;
    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

    private Double quantity;

    private LocalDateTime dateTime;

    public Transaction( FuelStation fuelStation, Fuel fuel, Vehicle vehicle, Employee employee, Double quantity, LocalDateTime dateTime) {

        this.fuelStation = fuelStation;
        this.fuel = fuel;
        this.vehicle = vehicle;
        this.employee = employee;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }
}

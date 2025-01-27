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

    public Transaction(Double quantity, LocalDateTime dateTime) {
        this.quantity = quantity;
        this.dateTime = dateTime;
    }
}

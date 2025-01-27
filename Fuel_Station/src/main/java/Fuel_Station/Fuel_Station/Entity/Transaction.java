package Fuel_Station.Fuel_Station.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="Transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

@ManyToOne
@JoinColumn(name = "stationId")
private FuelStation FuelStation;
    @ManyToOne
    @JoinColumn(name = "fuelId")
    private FuelEntity fuel;
    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

    private Double quantity;

    private LocalDateTime dateTime;







}

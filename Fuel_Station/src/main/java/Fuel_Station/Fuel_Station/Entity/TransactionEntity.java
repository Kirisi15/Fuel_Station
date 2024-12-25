package Fuel_Station.Fuel_Station.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Transaction")

public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

@ManyToOne
@JoinColumn(name = "stationId",referencedColumnName = "stationId",nullable = false)
private FuelStationEntity Fuelstation;
    @ManyToOne
    @JoinColumn(name = "fuelId", referencedColumnName = "fuelId", nullable = false)
    private FuelEntity fuel;
    @ManyToOne
    @JoinColumn(name = "vehicleId",referencedColumnName = "vehicleId",nullable = false)
    private VehicleEntity vehicle;
    @ManyToOne
    @JoinColumn(name = "empId",referencedColumnName = "empId",nullable = false)
    private EmployeeEntity employee;

    private Double quantity;

    private LocalDateTime dateTime;

    public TransactionEntity(){

    }
    public TransactionEntity(Long transactionId,Double quantity,LocalDateTime dateTime){
        this.transactionId=transactionId;


        this.quantity= quantity;
        this.dateTime=dateTime;
    }
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;}





    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "transactionId=" + transactionId +

                ", quantity=" + quantity +
                ", dateTime=" + dateTime +
                '}';
    }
}

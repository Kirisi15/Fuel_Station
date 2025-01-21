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
@JoinColumn(name = "stationId")
private FuelStationEntity FuelStation;
    @ManyToOne
    @JoinColumn(name = "fuelId")
    private FuelEntity fuel;
    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private VehicleEntity vehicle;
    @ManyToOne
    @JoinColumn(name = "empId")
    private EmployeeEntity employee;

    private Double quantity;

    private LocalDateTime dateTime;

    public TransactionEntity(){

    }
    public TransactionEntity(FuelStationEntity FuelStation,Long transactionId,Double quantity,LocalDateTime dateTime){
        this.transactionId=transactionId;
        this.FuelStation=FuelStation;


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
    public FuelStationEntity getFuelStation() {
        return FuelStation;
    }

    public void setFuelStation(FuelStationEntity FuelStation) {
        this.FuelStation = FuelStation;
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

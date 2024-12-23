package Fuel_Station.Fuel_Station.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Transaction")

public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String fuelType;

    private Long employeeId;

    private Double quantity;

    private LocalDateTime dateTime;

    public TransactionEntity(){

    }
    public TransactionEntity(Long transactionId,String fuelType,Long employeeId,Double quantity,LocalDateTime dateTime){
        this.transactionId=transactionId;
        this.fuelType=fuelType;
        this.employeeId=employeeId;
        this.quantity= quantity;
        this.dateTime=dateTime;
    }
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

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
                ", fuelType='" + fuelType + '\'' +
                ", employeeId=" + employeeId +
                ", quantity=" + quantity +
                ", dateTime=" + dateTime +
                '}';
    }
}

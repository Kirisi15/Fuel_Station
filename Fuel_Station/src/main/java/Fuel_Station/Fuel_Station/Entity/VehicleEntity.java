package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long VehicleId;
    @Column(name="Vehicle_Number")
    private String VehicleNumber;
    @Column(name="Vehicle_Type")
    private String VehicleType;
    @Column(name="Fuel_Type")
    private String FuelType;

    public VehicleEntity() {
    }

    public VehicleEntity(long vehicleId, String vehicleNumber, String vehicleType, String fuelType) {
        VehicleId = vehicleId;
        VehicleNumber = vehicleNumber;
        VehicleType = vehicleType;
        FuelType = fuelType;
    }

    public long getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(long vehicleId) {
        VehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "VehicleId='" + VehicleId + '\'' +
                ", VehicleNumber='" + VehicleNumber + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                ", FuelType='" + FuelType + '\'' +
                '}';
    }
}

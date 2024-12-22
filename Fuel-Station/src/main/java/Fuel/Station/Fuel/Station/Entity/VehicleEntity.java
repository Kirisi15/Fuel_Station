package Fuel.Station.Fuel.Station.Entity;



import jakarta.persistence.*;

@Entity
@Table(name="Vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String VehicleId;
    @Column
    private String VehicleNumber;
    @Column
    private String VehicleType;
    @Column
    private String FuelType;

    public VehicleEntity() {
    }

    public VehicleEntity(String vehicleId, String vehicleNumber, String vehicleType, String fuelType) {
        VehicleId = vehicleId;
        VehicleNumber = vehicleNumber;
        VehicleType = vehicleType;
        FuelType = fuelType;
    }

    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
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


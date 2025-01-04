package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VehicleId;

    @Column(name="Vehicle_Number")
    private String VehicleNumber;
    @Column(name="Vehicle_Type")
    private String VehicleType;
    @Column(name="Fuel_Type")
    private String FuelType;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Vehicle_FuelStation",
            joinColumns = @JoinColumn(name = "VehicleId"),
            inverseJoinColumns = @JoinColumn(name = "StationId")
    )
    private List<FuelStationEntity> fuelStations = new ArrayList<>();


    public VehicleEntity() {
    }


    public VehicleEntity(Long vehicleId, String vehicleNumber, String vehicleType, String fuelType) {

        VehicleId = vehicleId;
        VehicleNumber = vehicleNumber;
        VehicleType = vehicleType;
        FuelType = fuelType;
    }


    public Long getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(Long vehicleId) {

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

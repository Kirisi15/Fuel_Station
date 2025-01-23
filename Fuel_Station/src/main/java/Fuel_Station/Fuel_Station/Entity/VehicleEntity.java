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

    @Column(name="Fuel_Limit")
    private int FuelLimit;

    @ManyToOne
    @JoinColumn(name = "customer_id")
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

//    public VehicleEntity( Long vehicleId, String vehicleNumber, String vehicleType, String fuelType, int fuelLimit,CustomerEntity customer) {
//
//        VehicleId = vehicleId;
//        VehicleNumber = vehicleNumber;
//        VehicleType = vehicleType;
//        FuelType = fuelType;
//        FuelLimit = fuelLimit;
//        CustomerEntity Customer = customer;
//    }


    public VehicleEntity(Long vehicleId, String vehicleNumber, String vehicleType, String fuelType, int fuelLimit, CustomerEntity customer) {
        VehicleId = vehicleId;
        VehicleNumber = vehicleNumber;
        VehicleType = vehicleType;
        FuelType = fuelType;
        FuelLimit = fuelLimit;
        this.customer = customer;
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

    public int getFuelLimit() {
        return FuelLimit;
    }

    public void setFuelLimit(int fuelLimit) {
        FuelLimit = fuelLimit;
    }
    public  CustomerEntity getCustomer(){
        return customer;
    }
    public void setCustomer(CustomerEntity customer){
        this.customer=customer;
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




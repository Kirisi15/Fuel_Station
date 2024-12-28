package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="FuelStation")
public class FuelStationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;
@ManyToOne
@JoinColumn(name = "ownerId",referencedColumnName = "ownerId",nullable = false)
private OwnerEntity owner;
@OneToMany(mappedBy = "fuelStation",cascade =CascadeType.ALL )
private List<EmployeeEntity> employee=new ArrayList<>();
@OneToMany(mappedBy = "FuelStation",cascade = CascadeType.ALL)
private List<TransactionEntity> transaction =new ArrayList<>();
@ManyToMany(mappedBy = "fuelStations")
private List<VehicleEntity> vehicle = new ArrayList<>();



@ManyToMany
@JoinTable(name = "FuelStation_Fuel",joinColumns =@JoinColumn(name = "stationId") ,inverseJoinColumns = @JoinColumn(name = "fuelId"))
private List<FuelEntity> fuel = new ArrayList<>();

    private String stationName;
    private String fuelType;
    private String address;
    private String licenseNumber;
    private String contactNumber;




    public FuelStationEntity() {
    }


    public FuelStationEntity(String stationName, String fuelType, String address, String licenseNumber, String contactNumber) {
        this.stationName = stationName;
        this.fuelType = fuelType;
        this.address = address;
        this.licenseNumber = licenseNumber;
        this.contactNumber = contactNumber;
    }


    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "FuelStationEntity{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", address='" + address + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}

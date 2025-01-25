package Fuel_Station.Fuel_Station.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JoinColumn(name = "ownerId")
@JsonIgnore
private OwnerEntity owner;
@OneToMany(mappedBy = "fuelStation",cascade =CascadeType.ALL )
private List<EmployeeEntity> employee=new ArrayList<>();
@OneToMany(mappedBy = "FuelStation",cascade = CascadeType.ALL)
private List<TransactionEntity> transaction =new ArrayList<>();
@ManyToMany(mappedBy = "fuelStations")
private List<Vehicle> vehicle = new ArrayList<>();

@ManyToMany
@JsonIgnore
@JoinTable(name = "FuelStation_Fuel",joinColumns =@JoinColumn(name = "stationId") ,inverseJoinColumns = @JoinColumn(name = "fuelId"))
private List<FuelEntity> fuel = new ArrayList<>();


    private String stationName;
    private String address;
    private String licenseNumber;
    private String contactNumber;




    public FuelStationEntity() {
    }


    public FuelStationEntity(String stationName, String address, String licenseNumber, String contactNumber) {
        this.stationName = stationName;
        this.address = address;
        this.licenseNumber = licenseNumber;
        this.contactNumber = contactNumber;
    }

    public List<EmployeeEntity> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeEntity> employee) {
        this.employee = employee;
    }

    public List<TransactionEntity> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionEntity> transaction) {
        this.transaction = transaction;
    }

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    public List<FuelEntity> getFuel() {
        return fuel;
    }

    public void setFuel(List<FuelEntity> fuel) {
        this.fuel = fuel;
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
    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "FuelStationEntity{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", address='" + address + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}

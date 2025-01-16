package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="owner")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<FuelStationEntity> fuelStations = new ArrayList<>();


    @Column(name="name")
    private String name;

    @Column(name="nic")
    private String nic;

    @Column(name="contact_number")
    private String contactNumber;

    @Column(name="email")
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public OwnerEntity(Long ownerId, String name, String nic, String contactNumber, String email, String username, String password) {
        this.ownerId = ownerId;
        this.name = name;
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public OwnerEntity() {
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<FuelStationEntity> getFuelStations() {
        return fuelStations;
    }

    public void setFuelStations(List<FuelStationEntity> fuelStations) {
        this.fuelStations = fuelStations;
    }

    @Override
    public String toString() {
        return "OwnerEntity{" +
                "ownerId=" + ownerId +
                ", fuelStations=" + fuelStations +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", contact_number='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



}


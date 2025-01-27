package Fuel_Station.Fuel_Station.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<FuelStation> fuelStations = new ArrayList<>();


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
    public Owner() {
        // Default constructor required by JPA
    }

    public Owner(Long ownerId, String name, String nic, String contactNumber, String email, String username, String password) {
        this.ownerId = ownerId;
        this.name = name;
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
@JsonIgnore
    public List<FuelStation> getFuelStations() {
        return fuelStations;
    }

    public void setFuelStations(List<FuelStation> fuelStations) {
        this.fuelStations = fuelStations;
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

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", fuelStations=" + fuelStations +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


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
@NoArgsConstructor
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

    public Owner(String name, String nic, String contactNumber, String email, String username, String password) {
        this.name = name;
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}


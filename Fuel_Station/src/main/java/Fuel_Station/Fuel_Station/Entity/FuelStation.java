package Fuel_Station.Fuel_Station.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FuelStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    @JsonIgnore
    private Owner owner;
    @OneToMany(mappedBy = "fuelStation",cascade =CascadeType.ALL )
    private List<Employee> employee=new ArrayList<>();
    @OneToMany(mappedBy = "fuelStation",cascade = CascadeType.ALL)
    private List<Transaction> transaction =new ArrayList<>();
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
}

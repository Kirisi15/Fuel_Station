package Fuel_Station.Fuel_Station.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="FuelStation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
@OneToMany(mappedBy = "FuelStation",cascade = CascadeType.ALL)
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

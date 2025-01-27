package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="fuel")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fuelId;
    @Column(name = "fuel_type")
    private String fuelType;
    @ManyToMany(mappedBy = "fuel")
    private List<FuelStation> fuelStations = new ArrayList<>();
    public Fuel(String fuelType) {
        this.fuelType = fuelType;
    }
}
package Fuel_Station.Fuel_Station.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuelResponse {
    private long fuelId;
    private String fuelType;
    private double addedFuel;
    private double pumpedFuel;
}

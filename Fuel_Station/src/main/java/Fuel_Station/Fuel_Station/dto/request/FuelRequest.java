package Fuel_Station.Fuel_Station.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FuelRequest {
    private String fuelType;
}

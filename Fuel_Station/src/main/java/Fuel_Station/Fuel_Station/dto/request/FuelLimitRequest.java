package Fuel_Station.Fuel_Station.dto.request;

import Fuel_Station.Fuel_Station.enums.VehicleType;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FuelLimitRequest {
    private VehicleType vehicleType;
    private int fuelLimit;
}

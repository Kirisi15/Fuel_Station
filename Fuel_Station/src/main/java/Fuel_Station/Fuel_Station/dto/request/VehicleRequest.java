package Fuel_Station.Fuel_Station.dto.request;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.FuelLimit;
import Fuel_Station.Fuel_Station.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class VehicleRequest {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private String fuelType;
    private FuelLimit fuelLimitId;
    private Long customerId;
}

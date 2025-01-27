package Fuel_Station.Fuel_Station.dto.request;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.FuelLimit;
import Fuel_Station.Fuel_Station.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class VehicleRequest {
    private VehicleType vehicleType;
    private String FuelType;
    private FuelLimit fuelLimitId;
    private Customer customer;
}

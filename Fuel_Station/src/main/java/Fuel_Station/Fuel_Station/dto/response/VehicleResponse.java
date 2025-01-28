package Fuel_Station.Fuel_Station.dto.response;


import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.FuelLimit;
import Fuel_Station.Fuel_Station.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponse {
    private Long vehicleId;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private String fuelType;
    private FuelLimit fuelLimitId;
}

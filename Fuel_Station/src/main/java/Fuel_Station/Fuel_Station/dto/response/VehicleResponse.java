package Fuel_Station.Fuel_Station.dto.response;


import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.FuelLimit;
import Fuel_Station.Fuel_Station.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponse {

    private String VehicleNumber;
    private VehicleType vehicleType;
    private String FuelType;
    private FuelLimit fuelLimitId;
    private Customer customer;


}

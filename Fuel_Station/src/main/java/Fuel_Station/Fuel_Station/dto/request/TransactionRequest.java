package Fuel_Station.Fuel_Station.dto.request;

import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Entity.Fuel;
import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Vehicle;

import lombok.Data;

import java.time.LocalDateTime;
@Data

public class TransactionRequest {
    private Double quantity;
    private Employee employee;
    private Vehicle vehicle;
    private FuelStation fuelStation;
    private Fuel fuel;

    private LocalDateTime dateTime;
}

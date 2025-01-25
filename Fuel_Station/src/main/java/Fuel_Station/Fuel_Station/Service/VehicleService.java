package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicleEntity, Long customerId);

    Vehicle getVehicleById(Long VehicleId);

    List<Vehicle> getAllVehicles();

    Vehicle updateVehicle(Vehicle vehicleEntity);

    void deleteVehicle(Long VehicleId);
}

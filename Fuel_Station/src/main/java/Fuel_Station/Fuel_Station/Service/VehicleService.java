package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.VehicleEntity;

import java.util.List;

public interface VehicleService {
    VehicleEntity createVehicle(VehicleEntity vehicleEntity);

    VehicleEntity getVehicleById(Long VehicleId);

    List<VehicleEntity> getAllVehicles();

    VehicleEntity updateVehicle(VehicleEntity vehicleEntity);

    void deleteVehicle(Long VehicleId);
}

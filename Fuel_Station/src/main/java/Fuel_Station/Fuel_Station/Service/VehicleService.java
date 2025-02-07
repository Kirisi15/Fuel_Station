package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.dto.VehicleScanResponse;
import Fuel_Station.Fuel_Station.dto.request.VehicleRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    ResponseEntity<?> createVehicle(VehicleRequest vehicle);

    ResponseEntity<?> getVehicleById(Long VehicleId);

    Vehicle getById(Long vehicleId);

    ResponseEntity<?> getAllVehicles();


    ResponseEntity<?> deleteVehicle(Long VehicleId);

    ResponseEntity<?> getVehicleByOwnerId(Long vehicleId);

    VehicleScanResponse scan(Long vehicleId) throws Exception;

    ResponseEntity<?> getVehicleBycustomerId(Long customerId);
    public boolean validateAndRegisterVehicle(String licenseNumber, Long customerId);
}

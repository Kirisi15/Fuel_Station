package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.dto.VehicleScanResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    ResponseEntity<?> addVehicle(Vehicle vehicleEntity, Long customerId);

    ResponseEntity<?> getVehicleById(Long VehicleId);

    ResponseEntity<?> getById(Long vehicleId);

    ResponseEntity<?> getAllVehicles();


    ResponseEntity<?> deleteVehicle(Long VehicleId);

    ResponseEntity<?> getVehicleByOwnerId(Long vehicleId);

    VehicleScanResponse scan(Long vehicleId) throws Exception;

    ResponseEntity<?> getVehicleBycustomerId(Long customerId);
}

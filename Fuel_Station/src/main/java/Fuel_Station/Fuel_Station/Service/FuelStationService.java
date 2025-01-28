package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.dto.request.FuelStationRequest;
import org.springframework.http.ResponseEntity;

public interface FuelStationService {
    FuelStation getById(Long id);
    ResponseEntity<?> getAllStations();
    ResponseEntity<?> getStationById(Long stationId);
    ResponseEntity<?> addStation(FuelStationRequest fuelStation);
    ResponseEntity<?> updateStation(Long stationId, FuelStationRequest fuelStation);
    ResponseEntity<?> deleteStation(Long stationId);
    ResponseEntity<?> getStationByOwnerId(Long ownerId);
//    ResponseEntity<?> saveFuelStation(FuelStation fuelStation);
}

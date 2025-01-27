package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Fuel;
import Fuel_Station.Fuel_Station.dto.request.FuelRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FuelService {
    Fuel getById(Long id);
    ResponseEntity<?> createFuel(FuelRequest fuelRequest);
    ResponseEntity<?> getAllFuels();
}

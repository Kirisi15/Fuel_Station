package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Fuel;

import java.util.List;
import java.util.Optional;

public interface FuelService {
    Fuel createFuel(Fuel fuel);

    List<Fuel> getAllFuels();

    List<Fuel> getFuelsByStationId(Long stationId);

     Optional<Fuel> getFuelById(Long fuelId);

    Fuel updateFuel(Fuel fuel);
}

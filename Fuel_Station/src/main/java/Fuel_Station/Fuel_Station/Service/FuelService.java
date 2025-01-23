package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;

import java.util.List;
import java.util.Optional;

public interface FuelService {
    FuelEntity createFuel(FuelEntity fuelEntity);

    List<FuelEntity> getAllFuels();

    List<FuelEntity> getFuelsByStationId(Long stationId);

     Optional<FuelEntity> getFuelById(Long fuelId);

    FuelEntity updateFuel(FuelEntity fuel);
}

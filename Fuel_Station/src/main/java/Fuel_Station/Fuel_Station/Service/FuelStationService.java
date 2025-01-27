package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStation;

import java.util.List;

public interface FuelStationService {
    List<FuelStation> getAllStations();
    FuelStation getStationById(Long stationId);
    FuelStation addStation(FuelStation fuelStation, Long ownerId);
    FuelStation updateStation(Long stationId, FuelStation fuelStation);
    void deleteStation(Long stationId);
    List <FuelStation> getStationByOwnerId(Long ownerId) ;
    FuelStation saveFuelStation(FuelStation fuelStation);
}

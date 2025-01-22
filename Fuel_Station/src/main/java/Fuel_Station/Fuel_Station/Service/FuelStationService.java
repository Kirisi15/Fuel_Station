package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;

import java.util.List;

public interface FuelStationService {
    List<FuelStationEntity> getAllStations();
    FuelStationEntity getStationById(Long stationId);
    FuelStationEntity addStation(FuelStationEntity fuelStation,Long ownerId);
    FuelStationEntity updateStation(Long stationId, FuelStationEntity fuelStation);
    void deleteStation(Long stationId);

    FuelStationEntity saveFuelStation(FuelStationEntity fuelStation);
}

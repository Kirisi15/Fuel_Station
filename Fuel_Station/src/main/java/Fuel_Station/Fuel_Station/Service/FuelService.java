package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FuelService {
    FuelEntity getFuelById(Long fuelId);

    FuelEntity createFuel(FuelEntity fuelEntity, Long ownerId, Long stationId);
    FuelEntity getFuelById(long Fuel_Id);
    List< FuelEntity> getAllFuels();
    FuelEntity updatefuel(Long fuelId, FuelEntity FuelEntity);
    void deletefuel(Integer fuel_Id);

    FuelEntity addfuel(FuelEntity fuelStation);

    FuelEntity updateStation(Long stationId, FuelStationEntity fuelStation);

    void deleteFuel(Long fuelId);

    FuelEntity updateFuel(FuelEntity fuelEntity);

    List<FuelEntity> getAllFuel();
}


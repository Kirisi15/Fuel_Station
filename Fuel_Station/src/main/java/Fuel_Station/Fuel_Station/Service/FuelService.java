package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;

import java.util.List;

public interface FuelService {
    FuelEntity getFuelById(Long fuelId);

    FuelEntity createFuel(FuelEntity fuelEntity);
    FuelEntity getFuelById(long Fuel_Id);
    List< FuelEntity> getAllFuels();
    FuelEntity updatefuel(FuelEntity FuelEntity);
    void deletefuel(Integer fuel_Id);

}


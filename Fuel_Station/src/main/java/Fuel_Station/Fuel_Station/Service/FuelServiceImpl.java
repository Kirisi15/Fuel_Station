package Fuel_Station.Fuel_Station.Service.Impl;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Repository.FuelRepository;
import Fuel_Station.Fuel_Station.Service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    private FuelRepository fuelRepository;

    @Override
    public FuelEntity createFuel(FuelEntity fuelEntity) {
      FuelEntity fuelEntity1=new FuelEntity();
      fuelEntity1.setFuelType(fuelEntity.getFuelType());
      fuelEntity1.setAddedFuel(fuelEntity.getAddedFuel());
      fuelEntity1.setPumpedFuel(fuelEntity.getPumpedFuel());
      return fuelRepository.save(fuelEntity1);

    }
}
package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private FuelStationService fuelStationService;

    @Override
    public FuelEntity createFuel(FuelEntity fuelEntity) {
      FuelEntity fuelEntity1=new FuelEntity();
      fuelEntity1.setFuelType(fuelEntity.getFuelType());
      fuelEntity1.setAddedFuel(fuelEntity.getAddedFuel());
      fuelEntity1.setPumpedFuel(fuelEntity.getPumpedFuel());
      return fuelRepository.save(fuelEntity1);

    }

    public List<FuelEntity> getAllFuels() {
        return fuelRepository.findAll();
    }

    public List<FuelEntity> getFuelsByStationId(Long stationId) {
        FuelStationEntity fuelStation = fuelStationService.getStationById(stationId);
        return fuelStation.getFuel();
    }

    @Override
    public Optional<FuelEntity> getFuelById(Long fuelId) {
        return fuelRepository.findById(fuelId);
    }

    @Override
    public FuelEntity updateFuel(FuelEntity fuel) {
        if (!fuelRepository.existsById(fuel.getFuelId())) {
            throw new RuntimeException("Fuel not found for update");
        }
        return fuelRepository.save(fuel);
    }



}
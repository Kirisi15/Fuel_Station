package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Fuel;
import Fuel_Station.Fuel_Station.Entity.FuelStation;
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
    public Fuel createFuel(Fuel fuel) {
      Fuel fuel1 =new Fuel();
      fuel1.setFuelType(fuel.getFuelType());
      fuel1.setAddedFuel(fuel.getAddedFuel());
      fuel1.setPumpedFuel(fuel.getPumpedFuel());
      return fuelRepository.save(fuel1);

    }

    public List<Fuel> getAllFuels() {
        return fuelRepository.findAll();
    }

    public List<Fuel> getFuelsByStationId(Long stationId) {
        FuelStation fuelStation = fuelStationService.getStationById(stationId);
        return fuelStation.getFuel();
    }

    @Override
    public Optional<Fuel> getFuelById(Long fuelId) {
        return fuelRepository.findById(fuelId);
    }

    @Override
    public Fuel updateFuel(Fuel fuel) {
        if (!fuelRepository.existsById(fuel.getFuelId())) {
            throw new RuntimeException("Fuel not found for update");
        }
        return fuelRepository.save(fuel);
    }



}
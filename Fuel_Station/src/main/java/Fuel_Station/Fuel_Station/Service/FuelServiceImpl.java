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

    @Override
    public List<FuelEntity> getAllFuels() {
        return fuelRepository.findAll();
    }

    @Override
    public FuelEntity getFuelById(Long fuelId) {
        Optional<FuelEntity> fuel = fuelRepository.findById(Math.toIntExact(fuelId));
        return fuel.orElseThrow(() -> new RuntimeException("Fuel not found with ID: " + fuelId));
    }

    @Override
    public FuelEntity createFuel(FuelEntity fuelEntity) {
        return fuelRepository.save(fuelEntity);
    }

    @Override
    public FuelEntity getFuelById(long Fuel_Id) {
        return null;
    }

    @Override
    public FuelEntity updatefuel(Long fuelId, FuelEntity fuelEntity) {
        FuelEntity existingFuel = getFuelById((long) fuelEntity.getFuelId());
        existingFuel.setFuelType(fuelEntity.getFuelType());
        existingFuel.setRemailFuel(fuelEntity.getRemailFuel());
        return fuelRepository.save(existingFuel);
    }

    @Override
    public void deletefuel(Integer fuelId) {
        getFuelById(Long.valueOf(fuelId)); // Ensures fuel exists before attempting to delete.
        fuelRepository.deleteById(fuelId);
    }

    @Override
    public FuelEntity addfuel(FuelEntity fuelStation) {
        return null;
    }

    @Override
    public FuelEntity updateStation(Long stationId, FuelStationEntity fuelStation) {
        return null;
    }

    @Override
    public void deleteFuel(Long fuelId) {

    }

    @Override
    public FuelEntity updateFuel(FuelEntity fuelEntity) {
        return null;
    }

    @Override
    public List<FuelEntity> getAllFuel() {
        return null;
    }
}

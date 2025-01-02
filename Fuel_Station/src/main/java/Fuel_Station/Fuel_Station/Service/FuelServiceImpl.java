package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import Fuel_Station.Fuel_Station.Repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class FuelServiceImpl implements FuelService {

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
    public FuelEntity updatefuel(FuelEntity fuelEntity) {
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
}

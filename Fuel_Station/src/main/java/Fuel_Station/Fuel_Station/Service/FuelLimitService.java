package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelLimit;
import Fuel_Station.Fuel_Station.Repository.FuelLimitRepository;
import Fuel_Station.Fuel_Station.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelLimitService {

    @Autowired
    private FuelLimitRepository fuelLimitRepository;

    public List<FuelLimit> getAllFuelLimits() {
        return fuelLimitRepository.findAll();
    }

    public Optional<FuelLimit> getFuelLimitByVehicleType(VehicleType vehicleType) {
        return fuelLimitRepository.findByVehicleType(vehicleType);
    }

    public FuelLimit addFuelLimit(FuelLimit fuelLimit) {
        return fuelLimitRepository.save(fuelLimit);
    }

    public Optional<FuelLimit> updateFuelLimit(int id, FuelLimit fuelLimitDetails) {
        return fuelLimitRepository.findById(id).map(fuelLimit -> {
            fuelLimit.setVehicleType(fuelLimitDetails.getVehicleType());
            fuelLimit.setFuelLimit(fuelLimitDetails.getFuelLimit());
            return fuelLimitRepository.save(fuelLimit);
        });
    }

    public boolean deleteFuelLimit(int id) {
        if (fuelLimitRepository.existsById(id)) {
            fuelLimitRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

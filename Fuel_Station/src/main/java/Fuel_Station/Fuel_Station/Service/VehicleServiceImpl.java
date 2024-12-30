package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.VehicleEntity;
import Fuel_Station.Fuel_Station.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;


    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleEntity createVehicle(VehicleEntity vehicleEntity){
        return vehicleRepository.save(vehicleEntity);
    }

    @Override
    public VehicleEntity getVehicleById(Long vehicleId){
        Optional<VehicleEntity> optionalVehicleEntity=vehicleRepository.findById(vehicleId);
        return optionalVehicleEntity.get();
    }

    @Override
    public List<VehicleEntity> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public VehicleEntity updateVehicle(VehicleEntity vehicleEntity) {
        VehicleEntity existingVehicle = vehicleRepository.findById(vehicleEntity.getVehicleId()).orElse(null);

            existingVehicle.setVehicleNumber(vehicleEntity.getVehicleNumber());
            existingVehicle.setVehicleType(vehicleEntity.getVehicleType());
            existingVehicle.setFuelType(vehicleEntity.getFuelType());

            return vehicleRepository.save(existingVehicle);
    }

    @Override
    public void deleteVehicle(Long VehicleId){
        vehicleRepository.deleteById(VehicleId);
    }


}

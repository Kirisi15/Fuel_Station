package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.Repository.CustomerRepository;
import Fuel_Station.Fuel_Station.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private final VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRepository customerRepository;



    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicleEntity, Long customerId){
        Customer customer= customerRepository.findById(customerId).get();
        vehicleEntity.setCustomer(customer);
        return vehicleRepository.save(vehicleEntity);
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId){
        Optional<Vehicle> optionalVehicleEntity=vehicleRepository.findById(vehicleId);
        return optionalVehicleEntity.get();
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicleEntity) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleEntity.getVehicleId()).orElse(null);

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

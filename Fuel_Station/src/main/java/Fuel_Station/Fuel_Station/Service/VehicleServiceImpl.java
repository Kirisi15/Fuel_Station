package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.*;
import Fuel_Station.Fuel_Station.Repository.*;
import Fuel_Station.Fuel_Station.dto.VehicleScanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private final VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FuelLimitRepository fuelLimitRepository;

    @Autowired
    private TimePeriodRepository timePeriodRepository;

    @Autowired
    private VehicleFuelQuoteRepository vehicleFuelQuoteRepository;




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


    @Override
    public VehicleScanResponse scan(Long vehicleId) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if(vehicle.isEmpty()){
             throw new Exception("Vehicle not define");
        }
        TimePeriod lastTimePeriod = timePeriodRepository.findTopByOrderByCreatedDateDesc();

        VehicleScanResponse response = new VehicleScanResponse();
        if (lastTimePeriod != null) {
            Optional<VehicleFuelQuota> existingVehicleFuelQuota = vehicleFuelQuoteRepository.findByVehicleAndTimePeriod(vehicle.get(),lastTimePeriod);
            VehicleFuelQuota vehicleFuelQuota = new VehicleFuelQuota();
            if(existingVehicleFuelQuota.isEmpty()){

                vehicleFuelQuota.setVehicle(vehicle.get());
                vehicleFuelQuota.setTimePeriod(lastTimePeriod);
                vehicleFuelQuota.setPumpedFuel(0);
                vehicleFuelQuoteRepository.save(vehicleFuelQuota);
            }else{
                vehicleFuelQuota = existingVehicleFuelQuota.get();
            }
            Optional<FuelLimit> fuelLimit= fuelLimitRepository.findByVehicleType(vehicle.get().getVehicleType());
            VehicleScanResponse vehicleScanResponse = new VehicleScanResponse();

            if(fuelLimit.isEmpty()){
                throw new Exception("Fuel limit empty ");
            }
            vehicleScanResponse.setPumbed(vehicleFuelQuota.getPumpedFuel());
            vehicleScanResponse.setFuelLimit(fuelLimit.get().getFuelLimit());
            return vehicleScanResponse;
        }
        return null;
    }


}

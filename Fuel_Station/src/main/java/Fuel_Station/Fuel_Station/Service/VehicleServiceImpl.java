package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.*;
import Fuel_Station.Fuel_Station.Repository.*;
import Fuel_Station.Fuel_Station.dto.VehicleScanResponse;
import Fuel_Station.Fuel_Station.dto.request.VehicleRequest;
import Fuel_Station.Fuel_Station.dto.response.FuelStationResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import Fuel_Station.Fuel_Station.dto.response.VehicleResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private VehicledmtRepository vehicledmtRepository;

    @Autowired
    private CustomerService customerService;
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ResponseEntity<?> getById(Long vehicleId) {
        Long VehicleId;
        return vehicleRepository.getByVehicleId(VehicleId).orElse(null);
    }
    @Override
    public ResponseEntity<?> getAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<VehicleResponse> responses = new ArrayList<>();
        for(Vehicle vehicle:vehicleList){
            VehicleResponse response = new VehicleResponse(
                    vehicle.getVehicleId(),
                    vehicle.getVehicleType(),
                    vehicle.getFuelType(),
                    vehicle.getFuelLimitId(),
                    vehicle.getCustomer()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "All vehicles fetched successfully",
                        responses
                )
        );
    }

    @Override
    public ResponseEntity<?> addVehicle(Vehicle vehicleEntity, Long customerId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getVehicleById(Long vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if( optionalVehicle.isEmpty()){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Vehicle id not found",
                            null
                    )
            );
        }

        Vehicle vehicle = optionalVehicle.get();
        VehicleResponse response = new VehicleResponse(
                vehicle.getVehicleNumber(),
                vehicle.getVehicleType(),
                vehicle.getFuelType(),
                vehicle.getFuelLimitId(),
                vehicle.getCustomer()
        );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Vehicle fetched successfully",
                        response
                )
        );
    }

    @Transactional
    @Override
    public ResponseEntity<?> addVehicle(VehicleRequest vehicleRequest, Long vehicleId) {
        Customer customer =customerService.getByCustomerId(customerId)
        Vehicle vehicle;
        vehicle = new Vehicle(
                customer,
                vehicle.getVehicleType(),
                vehicle.getFuelType(),
                vehicle.getFuelLimitId(),
                vehicle.getVehicleNumber()
        );
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Vehicle added successfully",
                        null
                )
        );
    }


    @Override
    @Transactional
    public ResponseEntity<?> deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Vehicle deleted successfully",
                        null
                )
        );
    }

    @Override
    public ResponseEntity<?> getVehicleByOwnerId(Long vehicleId) {
        VehicleServiceImpl vehicleService = null;
        ResponseEntity<?> vehicle = vehicleService.getById(vehicleId);
        Customer customer = null;
        if(customer != null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "customer not found",
                            null
                    )
            );
        }
        List<Vehicle> vehicleList = vehicleRepository.findByCustomer(customer);
        List<VehicleResponse> responses = new ArrayList<>();
        for(Vehicle vehicle:vehicleList){
            VehicleResponse response = new FuelStationResponse(
                    vehicle.getVehicleId(),
                    vehicle.getVehicleNumber(),
                    vehicle.getFuelType(),
                    vehicle.getVehicleId()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "All vehicles fetched successfully",
                        responses
                )
        );
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

    @Override
    public ResponseEntity<?> getVehicleBycustomerId(Long customerId) {
        return null;
    }
//    public boolean validateAndRegisterVehicle(String licenseNumber, String nic) {
//        var optionalVehicledmt = vehicledmtRepository.findByLicenseNumberAndNic(licenseNumber, nic);
//        if (optionalVehicledmt.isPresent()) {
//            Vehicle vehicle = new Vehicle();
//            vehicle.setLicenseNumber(licenseNumber);
//            vehicle.setNic(nic);
//            vehicleRepository.save(vehicle);
//            return true;
//        }
//        return false;
   // }


}

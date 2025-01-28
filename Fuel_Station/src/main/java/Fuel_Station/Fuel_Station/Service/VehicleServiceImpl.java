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
    private  VehicleRepository vehicleRepository;
    @Autowired
    private FuelLimitRepository fuelLimitRepository;
    @Autowired
    private TimePeriodRepository timePeriodRepository;
    @Autowired
    private VehicleFuelQuoteRepository vehicleFuelQuoteRepository;
    @Autowired
    private CustomerService customerService;
    @Override
    public Vehicle getById(Long vehicleId) {
        return vehicleRepository.getByVehicleId(vehicleId).orElse(null);
    }
    @Override
    public ResponseEntity<?> getAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<VehicleResponse> responses = new ArrayList<>();
        for(Vehicle vehicle:vehicleList){
            VehicleResponse response = new VehicleResponse(
                    vehicle.getVehicleNumber(),
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
    public ResponseEntity<?> createVehicle(VehicleRequest vehicleRequest) {
        Customer customer = customerService.getById(vehicleRequest.getCustomerId());
        if(customer == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Customer not find with this id",
                            null
                    )
            );
        }
        Vehicle vehicle = new Vehicle(
                vehicleRequest.getVehicleNumber(),
                vehicleRequest.getVehicleType(),
                vehicleRequest.getFuelType(),
                vehicleRequest.getFuelLimitId(),
                customer
        );
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Vehicle saved successfully",
                        null
                )
        );
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
    public ResponseEntity<?> getVehicleByOwnerId(Long id) {
        Customer customer =customerService.getById(id);
        if(customer == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Customer not find with this id",
                            null
                    )
            );
        }
        List<Vehicle> vehicleList = vehicleRepository.findByCustomer(customer);
        List<VehicleResponse> responses = new ArrayList<>();
        for(Vehicle vehicle:vehicleList){
            VehicleResponse response = new VehicleResponse(
                    vehicle.getVehicleNumber(),
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
        Customer customer = customerService.getById(customerId);
        if (customer == null) {
            return ResponseEntity.ok().body(
                    new MessageResponse<>(400, "Customer not found with this ID", null)
            );
        }
        List<Vehicle> vehicleList = vehicleRepository.findByCustomer(customer);
        List<VehicleResponse> responses = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            VehicleResponse response = new VehicleResponse(
                    vehicle.getVehicleNumber(),
                    vehicle.getVehicleType(),
                    vehicle.getFuelType(),
                    vehicle.getFuelLimitId(),
                    vehicle.getCustomer()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(200, "Vehicles fetched successfully", responses)
        );
    }



}

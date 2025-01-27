package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.Repository.VehicleRepository;
import Fuel_Station.Fuel_Station.Service.VehicleService;
import Fuel_Station.Fuel_Station.dto.VehicleScanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/vehicle")
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;


    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping("/{customerId}")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicleEntity, @PathVariable Long customerId ){
        Vehicle savedVehicle= vehicleService.createVehicle(vehicleEntity,customerId);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long vehicleId){
        Vehicle vehicle=vehicleService.getVehicleById(vehicleId);
        return new ResponseEntity<>(vehicle,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        List<Vehicle> vehicles=vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable("id") Long vehicleId,
            @RequestBody Vehicle vehicleEntity){
        vehicleEntity.setVehicleId(vehicleId);
        Vehicle updatedVehicle= vehicleService.updateVehicle(vehicleEntity);
        return new ResponseEntity<>(updatedVehicle,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>("Vehicle Sucessfully deleted",HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCustomerId(@PathVariable Long customerId) {
        List<Vehicle> vehicles = vehicleRepository.findByCustomer_CustomerId(customerId);

        if (vehicles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 if no vehicles are found
        }
        return ResponseEntity.ok(vehicles);  // Return 200 and the vehicles list
    }

    @PutMapping("scan/{vehicleId}")
    public VehicleScanResponse vehicleScanner(@PathVariable Long vehicleId) throws Exception {
       return vehicleService.scan(vehicleId);
    }

}

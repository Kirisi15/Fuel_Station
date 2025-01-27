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
     VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;


    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicleById(@PathVariable("id") Long vehicleId){
        return vehicleService.getVehicleById(vehicleId);
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicleEntity, @PathVariable Long customerId ){
        return vehicleService.addVehicle(vehicleEntity,customerId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long vehicleId){
        return vehicleService.deleteVehicle(vehicleId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getVehiclesByCustomerId(@PathVariable("customerId") Long customerId) {
        return vehicleService.getVehicleBycustomerId(customerId);
    }

    @PutMapping("scan/{vehicleId}")
    public VehicleScanResponse vehicleScanner(@PathVariable Long vehicleId) throws Exception {
       return vehicleService.scan(vehicleId);
    }

}

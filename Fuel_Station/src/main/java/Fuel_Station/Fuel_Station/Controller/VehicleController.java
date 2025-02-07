package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.Repository.VehicleRepository;
import Fuel_Station.Fuel_Station.Service.VehicleService;
import Fuel_Station.Fuel_Station.dto.VehicleScanResponse;
import Fuel_Station.Fuel_Station.dto.request.VehicleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:3000"}, allowCredentials = "true")
@RequestMapping("api/vehicle")
public class VehicleController {
    @Autowired
    public VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<?> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }


    @PostMapping("")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleRequest vehicle){
        return vehicleService.createVehicle(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long vehicleId){
        return vehicleService.deleteVehicle(vehicleId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getVehiclesByCustomerId(@PathVariable("customerId") Long customerId) {
        return vehicleService.getVehicleBycustomerId(customerId);
    }

    @PutMapping("/{vehicleId}")
    public VehicleScanResponse vehicleScanner(@PathVariable Long vehicleId) throws Exception {
        System.out.println(vehicleId);
       return vehicleService.scan(vehicleId);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerVehicle(@RequestParam String licenseNumber, @RequestParam Long customerId) {
        boolean isRegistered = vehicleService.validateAndRegisterVehicle(licenseNumber, customerId);
        if (isRegistered) {
            return ResponseEntity.ok("Vehicle registered successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid license number or NIC.");
        }
    }

}

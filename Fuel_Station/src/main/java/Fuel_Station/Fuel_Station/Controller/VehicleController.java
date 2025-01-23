package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.VehicleEntity;
import Fuel_Station.Fuel_Station.Repository.VehicleRepository;
import Fuel_Station.Fuel_Station.Service.VehicleService;
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
    public ResponseEntity<VehicleEntity> createVehicle(@RequestBody VehicleEntity vehicleEntity,@PathVariable Long customerId ){
        VehicleEntity savedVehicle= vehicleService.createVehicle(vehicleEntity,customerId);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable("id") Long vehicleId){
        VehicleEntity vehicle=vehicleService.getVehicleById(vehicleId);
        return new ResponseEntity<>(vehicle,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VehicleEntity>> getAllVehicles(){
        List<VehicleEntity> vehicles=vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleEntity> updateVehicle(
            @PathVariable("id") Long vehicleId,
            @RequestBody VehicleEntity vehicleEntity){
        vehicleEntity.setVehicleId(vehicleId);
        VehicleEntity updatedVehicle= vehicleService.updateVehicle(vehicleEntity);
        return new ResponseEntity<>(updatedVehicle,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>("Vehicle Sucessfully deleted",HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<VehicleEntity>> getVehiclesByCustomerId(@PathVariable Long customerId) {
        List<VehicleEntity> vehicles = vehicleRepository.findByCustomer_CustomerId(customerId);

        if (vehicles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 if no vehicles are found
        }
        return ResponseEntity.ok(vehicles);  // Return 200 and the vehicles list
    }
}

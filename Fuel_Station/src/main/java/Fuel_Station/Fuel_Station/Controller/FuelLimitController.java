package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelLimit;
import Fuel_Station.Fuel_Station.enums.VehicleType;
import Fuel_Station.Fuel_Station.Service.FuelLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000,http://localhost:8081"})
@RequestMapping("api/fuelLimit")
public class FuelLimitController {

    @Autowired
    private FuelLimitService fuelLimitService;

    @GetMapping("/limits")
    public List<FuelLimit> getAllFuelLimits() {
        return fuelLimitService.getAllFuelLimits();
    }

    @GetMapping("/limit/{vehicleType}")
    public ResponseEntity<FuelLimit> getFuelLimitByVehicleType(@PathVariable VehicleType vehicleType) {
        return fuelLimitService.getFuelLimitByVehicleType(vehicleType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<FuelLimit> addFuelLimit(@RequestBody FuelLimit fuelLimit) {
        FuelLimit createdFuelLimit = fuelLimitService.addFuelLimit(fuelLimit);
        return ResponseEntity.ok(createdFuelLimit);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<FuelLimit> updateFuelLimit(
            @PathVariable int id,
            @RequestBody FuelLimit fuelLimit
    ) {
        return fuelLimitService.updateFuelLimit(id, fuelLimit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFuelLimit(@PathVariable int id) {
        boolean isDeleted = fuelLimitService.deleteFuelLimit(id);
        if (isDeleted) {
            return ResponseEntity.ok("Fuel limit deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


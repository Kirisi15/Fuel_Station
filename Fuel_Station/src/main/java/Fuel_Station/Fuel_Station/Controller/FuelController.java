package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fuel")
@CrossOrigin(origins = "http://localhost:3000")
public class FuelController {

    private final FuelService fuelService;

    @Autowired
    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping("/{stationId}/{ownerId}")
    public ResponseEntity<FuelEntity> createFuel(@RequestBody FuelEntity fuelEntity, @PathVariable Long ownerId, @PathVariable Long stationId) {
        FuelEntity savedFuel = fuelService.createFuel(fuelEntity, ownerId, stationId);
        return new ResponseEntity<>(savedFuel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelEntity> getFuelById(@PathVariable("id") Long fuelId) {
        FuelEntity fuel = fuelService.getFuelById(fuelId);
        return new ResponseEntity<>(fuel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FuelEntity>> getAllFuel() {
        List<FuelEntity> fuels = fuelService.getAllFuel();
        return new ResponseEntity<>(fuels, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuelEntity> updateFuel(
            @PathVariable("id") Long fuelId,
            @RequestBody FuelEntity fuelEntity) {
        fuelEntity.setFuelId(Math.toIntExact(fuelId));

        FuelEntity updatedFuel = fuelService.updateFuel(fuelEntity);
        return new ResponseEntity<>(updatedFuel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFuel(@PathVariable("id") Long fuelId) {
        fuelService.deleteFuel(fuelId);
        return new ResponseEntity<>("Fuel successfully deleted", HttpStatus.OK);
    }
}

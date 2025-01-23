package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Service.FuelService;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fuel")
@CrossOrigin(origins = "http://localhost:3000")
public class FuelController {

    @Autowired
    private FuelService fuelService;
    @Autowired
    private FuelStationService fuelStationService;

    @PostMapping("/{stationId}")
    public FuelEntity createFuel(@RequestBody FuelEntity fuelEntity, @PathVariable Long stationId) {
        FuelStationEntity fuelStation = fuelStationService.getStationById(stationId);
        if (fuelStation == null) {
            throw new RuntimeException("Fuel Station not found with ID: " + stationId);
        }
        FuelEntity createdFuel = fuelService.createFuel(fuelEntity);
        fuelStation.getFuel().add(createdFuel);
        fuelStationService.saveFuelStation(fuelStation);

        return createdFuel;
    }

    @GetMapping("/types/{stationId}")
    public List<Map<String, Object>> getFuelTypesByStationId(@PathVariable Long stationId) {
        FuelStationEntity fuelStation = fuelStationService.getStationById(stationId);
        if (fuelStation == null) {
            throw new RuntimeException("Fuel Station not found with ID: " + stationId);
        }

        List<Map<String, Object>> fuelData = new ArrayList<>();
        for (FuelEntity fuelEntity : fuelStation.getFuel()) {
            Map<String, Object> fuelInfo = new HashMap<>();
            fuelInfo.put("fuelId", fuelEntity.getFuelId());
            fuelInfo.put("fuelType", fuelEntity.getFuelType());
            fuelData.add(fuelInfo);
        }
        return fuelData;
    }

    @PutMapping("/add-fuel")
    public ResponseEntity<String> addFuelQuantity(
            @RequestBody Map<String, Object> payload) {
        Long stationId = Long.valueOf(payload.get("stationId").toString());
        String fuelType = payload.get("fuelType").toString();
        double quantity = Double.parseDouble(payload.get("quantity").toString());

        // Fetch the station by ID
        FuelStationEntity station = fuelStationService.getStationById(stationId);

        if (station == null) {
            return ResponseEntity.badRequest().body("Station not found");
        }
        FuelEntity fuel = station.getFuel().stream()
                .filter(f -> f.getFuelType().equals(fuelType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Fuel type not found"));
        fuel.setAddedFuel(fuel.getAddedFuel() + quantity);
        fuelService.updateFuel(fuel);

        return ResponseEntity.ok("Fuel quantity updated successfully");
    }



}
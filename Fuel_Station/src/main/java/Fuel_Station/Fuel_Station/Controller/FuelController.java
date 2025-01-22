package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Service.FuelService;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
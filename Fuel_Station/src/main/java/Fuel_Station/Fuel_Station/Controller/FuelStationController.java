package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import Fuel_Station.Fuel_Station.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/fuel-stations")
@CrossOrigin(origins = "http://localhost:3000")
public class FuelStationController {
    @Autowired
    private FuelStationService fuelStationService;
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<FuelStationEntity> getAllStations() {

        return fuelStationService.getAllStations();
    }

    @GetMapping("/{id}")
    public FuelStationEntity getStationById(@PathVariable("id") Long stationId) {
        return fuelStationService.getStationById(stationId);
    }

    @PostMapping
    public FuelStationEntity addStation(@RequestBody FuelStationEntity fuelStation) {
        return fuelStationService.addStation(fuelStation);
    }

    @PutMapping("/{id}")
    public FuelStationEntity updateStation(@PathVariable("id") Long stationId, @RequestBody FuelStationEntity fuelStation) {
        return fuelStationService.updateStation(stationId, fuelStation);
    }

    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable("id") Long stationId) {
        fuelStationService.deleteStation(stationId);
    }

}

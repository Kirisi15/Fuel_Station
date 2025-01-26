package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
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

    public FuelStationController(FuelStationService fuelStationService) {
        this.fuelStationService = fuelStationService;
    }

    @GetMapping
    public List<FuelStation> getAllStations() {

        return fuelStationService.getAllStations();
    }

    @GetMapping("/{stationId}")
    public FuelStation getStationById(@PathVariable("stationId") Long stationId) {
        return fuelStationService.getStationById(stationId);
    }

    @PostMapping("/{ownerId}")
    public FuelStation addStation(@RequestBody FuelStation fuelStation, @PathVariable Long ownerId) {
        return fuelStationService.addStation(fuelStation, ownerId);
    }

    @PutMapping("/{stationId}")
    public FuelStation updateStation(@PathVariable("stationId") Long stationId, @RequestBody FuelStation fuelStation) {
        return fuelStationService.updateStation(stationId, fuelStation);
    }

    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable("id") Long stationId) {
        fuelStationService.deleteStation(stationId);
    }


    @GetMapping("/owner/{ownerId}")
    public FuelStation getStationByOwnerId(@PathVariable("ownerId") Long ownerId) {
        return fuelStationService.getStationByOwnerId(ownerId);
    }
}

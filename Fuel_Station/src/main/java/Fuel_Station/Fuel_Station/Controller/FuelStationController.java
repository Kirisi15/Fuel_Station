package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Repository.FuelStationRepository;
import Fuel_Station.Fuel_Station.Repository.OwnerRepository;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import Fuel_Station.Fuel_Station.Service.OwnerService;
import Fuel_Station.Fuel_Station.dto.request.FuelStationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/fuelstation")
@CrossOrigin(origins = "http://localhost:3000")
public class FuelStationController {
   @Autowired
    FuelStationService fuelStationService;
    public FuelStationController(FuelStationService fuelStationService) {
        this.fuelStationService = fuelStationService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStations() {
        return fuelStationService.getAllStations();
    }

    @GetMapping("/{stationId}")
    public ResponseEntity<?> getStationById(@PathVariable("stationId") Long stationId) {
        return fuelStationService.getStationById(stationId);
    }

    @PostMapping("")
    public ResponseEntity<?> addStation(@RequestBody FuelStationRequest fuelStation) {
        return fuelStationService.addStation(fuelStation);
    }

    @PutMapping("/{stationId}")
    public ResponseEntity<?> updateStation(@PathVariable("stationId") Long stationId, @RequestBody FuelStationRequest fuelStation) {
        return fuelStationService.updateStation(stationId, fuelStation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable("id") Long stationId) {
        return fuelStationService.deleteStation(stationId);
    }



//    public FuelStation.java getStationByOwnerId(@PathVariable("ownerId") Long ownerId) {
//        return fuelStationService.getStationByOwnerId(ownerId);
//  }
   @GetMapping("/owners/{ownerId}")
public ResponseEntity<?> getStationByOwnerId(@PathVariable("ownerId") Long ownerId) {
    return fuelStationService.getStationByOwnerId(ownerId);
}


}


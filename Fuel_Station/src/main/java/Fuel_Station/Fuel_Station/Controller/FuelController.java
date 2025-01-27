package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Fuel;
import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Service.FuelService;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import Fuel_Station.Fuel_Station.dto.request.FuelRequest;
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

    @GetMapping("")
    public ResponseEntity<?> getAllFuel() {
        return fuelService.getAllFuels();
    }
    @PostMapping("")
    public ResponseEntity<?> createFuel(@RequestBody FuelRequest fuelRequest) {
        return fuelService.createFuel(fuelRequest);
    }
}
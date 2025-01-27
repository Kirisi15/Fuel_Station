package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.Fuel;
import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Repository.FuelRepository;
import Fuel_Station.Fuel_Station.dto.request.CustomerRequest;
import Fuel_Station.Fuel_Station.dto.request.FuelRequest;
import Fuel_Station.Fuel_Station.dto.response.FuelResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelServiceImpl implements FuelService {
    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private FuelStationService fuelStationService;
    @Override
    @Transactional
    public ResponseEntity<?> createFuel(FuelRequest fuelRequest) {
      Fuel fuel =new Fuel(fuelRequest.getFuelType());
      fuelRepository.save(fuel);
      return ResponseEntity.ok().body(
              new MessageResponse<>(
                      200,
                      "fuel created successfully",
                      null
              )
      );
    }
    public ResponseEntity<?> getAllFuels() {
        List<Fuel> fuels = fuelRepository.findAll();
        List<FuelResponse> responses = new ArrayList<>();
        for(Fuel fuel : fuels){
            FuelResponse response = new FuelResponse(
                    fuel.getFuelId(),
                    fuel.getFuelType()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "fuel fetched successfully",
                        responses
                )
        );
    }
}
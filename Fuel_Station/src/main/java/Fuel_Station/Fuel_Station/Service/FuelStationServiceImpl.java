package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.Repository.FuelStationRepository;
import Fuel_Station.Fuel_Station.dto.request.FuelStationRequest;
import Fuel_Station.Fuel_Station.dto.response.FuelStationResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public   class FuelStationServiceImpl implements FuelStationService {
    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private OwnerService ownerService;

    @Override
    public FuelStation getById(Long stationId) {
        return fuelStationRepository.getByStationId(stationId).orElse(null);
    }

    @Override
    public ResponseEntity<?> getAllStations() {
        List<FuelStation> fuelStationList = fuelStationRepository.findAll();
        List<FuelStationResponse> responses = new ArrayList<>();
        for(FuelStation fuelStation:fuelStationList){
            FuelStationResponse response = new FuelStationResponse(
                    fuelStation.getStationId(),
                    fuelStation.getStationName(),
                    fuelStation.getAddress(),
                    fuelStation.getLicenseNumber(),
                    fuelStation.getContactNumber()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "All fuel stations fetched successfully",
                        responses
                )
        );
    }

    @Override
    public ResponseEntity<?> getStationById(Long stationId) {
        Optional<FuelStation> optionalFuelStation = fuelStationRepository.findById(stationId);
        if( optionalFuelStation.isEmpty()){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Station id not found",
                            null
                    )
            );
        }

        FuelStation fuelStation = optionalFuelStation.get();
        FuelStationResponse response = new FuelStationResponse(
                fuelStation.getStationId(),
                fuelStation.getStationName(),
                fuelStation.getAddress(),
                fuelStation.getLicenseNumber(),
                fuelStation.getContactNumber()
        );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Fuel station fetched successfully",
                        response
                )
        );
    }

    @Transactional
    @Override
    public ResponseEntity<?> addStation(FuelStationRequest fuelStationRequest) {
        Owner owner = ownerService.getById(fuelStationRequest.getOwnerId());
        FuelStation fuelStation = new FuelStation(
                owner,
                fuelStationRequest.getStationName(),
                fuelStationRequest.getAddress(),
                fuelStationRequest.getLicenseNumber(),
                fuelStationRequest.getContactNumber()
        );
        fuelStationRepository.save(fuelStation);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Fuel station added successfully",
                        null
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateStation(Long stationId, FuelStationRequest fuelStation) {
        FuelStation existingStation = getById(stationId);
        if(existingStation == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Fuel station not found",
                            null
                    )
            );
        }
        existingStation.setStationName(fuelStation.getStationName());
        existingStation.setAddress(fuelStation.getAddress());
        existingStation.setLicenseNumber(fuelStation.getLicenseNumber());
        existingStation.setContactNumber(fuelStation.getContactNumber());
        fuelStationRepository.save(existingStation);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Fuel station updated successfully",
                        null
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteStation(Long stationId) {
        fuelStationRepository.deleteById(stationId);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Fuel station deleted successfully",
                        null
                )
        );
    }

   @Override
public ResponseEntity<?> getStationByOwnerId(Long ownerId) {
        Owner owner = ownerService.getById(ownerId);
        if(owner != null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Owner not found",
                            null
                    )
            );
        }
       List<FuelStation> fuelStationList = fuelStationRepository.findByOwner(owner);
       List<FuelStationResponse> responses = new ArrayList<>();
       for(FuelStation fuelStation:fuelStationList){
           FuelStationResponse response = new FuelStationResponse(
                   fuelStation.getStationId(),
                   fuelStation.getStationName(),
                   fuelStation.getAddress(),
                   fuelStation.getLicenseNumber(),
                   fuelStation.getContactNumber()
           );
           responses.add(response);
       }
       return ResponseEntity.ok().body(
               new MessageResponse<>(
                       200,
                       "All fuel stations fetched successfully",
                       responses
               )
       );
}



}
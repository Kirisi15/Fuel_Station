package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Repository.FuelStationRepository;
import Fuel_Station.Fuel_Station.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public   class FuelStationServiceImpl implements FuelStationService {

    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private OwnerService ownerService;

    @Override
    public List<FuelStation> getAllStations() {
        return fuelStationRepository.findAll();
    }

    @Override
    public FuelStation getStationById(Long stationId) {
        return fuelStationRepository.findById(stationId).orElse(null);
    }


    public FuelStation addStation(FuelStation fuelStation, Long ownerId) {
        // Fetch owner and associate with the fuel station
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found for ID: " + ownerId));

        fuelStation.setOwner(owner);
        return fuelStationRepository.save(fuelStation);
    }

    @Override
    public FuelStation updateStation(Long stationId, FuelStation fuelStation) {
        FuelStation existingStation = getStationById(stationId);
        existingStation.setStationName(fuelStation.getStationName());
        existingStation.setAddress(fuelStation.getAddress());
        existingStation.setLicenseNumber(fuelStation.getLicenseNumber());
        existingStation.setContactNumber(fuelStation.getContactNumber());
        return fuelStationRepository.save(existingStation);
    }

    @Override
    public void deleteStation(Long stationId) {
        fuelStationRepository.deleteById(stationId);
    }
//   @Override
//   public FuelStation.java getStationByOwnerId(Long ownerId) {
//       List<FuelStation.java> stations = fuelStationRepository.findByOwner_OwnerId(ownerId);
//
//       if (stations.isEmpty()) {
//           throw new RuntimeException("No fuel stations found for owner ID: " + ownerId);
//       }
//
//       // Return the first station if the list is not empty
//       return stations.get(0);
//   }

//public FuelStation.java getStationByOwnerId(Long ownerId) {
//       List<FuelStation.java> station = fuelStationRepository.findByOwner_OwnerId(ownerId);
//       return station.get(0) ;
//    }
   @Override
public List<FuelStation> getStationByOwnerId(Long ownerId) {
        Owner owner = ownerService.getById(ownerId);
        if(owner != null){
            return fuelStationRepository.findByOwner(owner);
        }
        return new ArrayList<>();
}


@Override
 public FuelStation saveFuelStation(FuelStation fuelStation) {
       return fuelStationRepository.save(fuelStation);
    }


}
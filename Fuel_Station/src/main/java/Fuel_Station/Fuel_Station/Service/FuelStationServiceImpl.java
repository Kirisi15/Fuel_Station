package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Repository.FuelStationRepository;
import Fuel_Station.Fuel_Station.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public   class FuelStationServiceImpl implements FuelStationService {

    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<FuelStation> getAllStations() {
        return fuelStationRepository.findAll();
    }

    @Override
    public FuelStation getStationById(Long stationId) {
        Optional<FuelStation> station = fuelStationRepository.findById(stationId);
        return station.get();
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
    @Override
    public FuelStation getStationByOwnerId(Long ownerId) {
        Optional<FuelStation> station = fuelStationRepository.findById(ownerId);
        return station.get();
    }

  @Override
 public FuelStation saveFuelStation(FuelStation fuelStation) {
       return fuelStationRepository.save(fuelStation);
    }


}
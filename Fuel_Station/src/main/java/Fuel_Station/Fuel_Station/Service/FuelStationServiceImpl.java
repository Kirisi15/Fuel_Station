package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
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
    public List<FuelStationEntity> getAllStations() {
        return fuelStationRepository.findAll();
    }

    @Override
    public FuelStationEntity getStationById(Long stationId) {
        Optional<FuelStationEntity> station = fuelStationRepository.findById(stationId);
        return station.get();
    }


    public FuelStationEntity addStation(FuelStationEntity fuelStation, Long ownerId) {
        // Fetch owner and associate with the fuel station
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found for ID: " + ownerId));

        fuelStation.setOwner(ownerEntity);
        return fuelStationRepository.save(fuelStation);
    }

    @Override
    public FuelStationEntity updateStation(Long stationId, FuelStationEntity fuelStation) {
        FuelStationEntity existingStation = getStationById(stationId);
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
    public FuelStationEntity getStationByOwnerId(Long ownerId) {
        Optional<FuelStationEntity> station = fuelStationRepository.findById(ownerId);
        return station.get();
    }

  @Override
 public FuelStationEntity saveFuelStation(FuelStationEntity fuelStation) {
       return fuelStationRepository.save(fuelStation);
    }


}
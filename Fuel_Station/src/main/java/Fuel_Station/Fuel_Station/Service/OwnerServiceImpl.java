package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
import Fuel_Station.Fuel_Station.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<OwnerEntity> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public OwnerEntity getOwnerById(Long ownerId) {
        Optional<OwnerEntity> owner = ownerRepository.findById(Math.toIntExact(ownerId));
        return owner.orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));
    }

    @Override
    public OwnerEntity createOwner(OwnerEntity ownerEntity) {
        return ownerRepository.save(ownerEntity);
    }

    @Override
    public OwnerEntity updateOwner(OwnerEntity ownerEntity) {
        OwnerEntity existingOwner = getOwnerById(ownerEntity.getOwnerId());
        existingOwner.setUsername(ownerEntity.getName());
        existingOwner.setContactNumber(ownerEntity.getContactNumber());
        return ownerRepository.save(existingOwner);
    }

    @Override
    public void deleteOwner(Long ownerId) {
        getOwnerById(ownerId); // Ensures owner exists before attempting to delete.
        ownerRepository.deleteById(Math.toIntExact(ownerId));
    }
    public Optional<OwnerEntity> findByUsername(String username) {
        return ownerRepository.findByUsername(username);
    }

}

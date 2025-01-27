package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getById(Long id) {
        return ownerRepository.findByOwnerId(id).orElse(null);
    }

    @Override
    public Owner getOwnerById(Long ownerId) {
        Optional<Owner> owner = ownerRepository.findById(ownerId);
        return owner.orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));
    }

    @Override
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        Owner existingOwner = getOwnerById(owner.getOwnerId());
        existingOwner.setName(owner.getName());
        existingOwner.setContactNumber(owner.getContactNumber());
        return ownerRepository.save(existingOwner);
    }

    @Override
    public void deleteOwner(Long ownerId) {
        getOwnerById(ownerId); // Ensures owner exists before attempting to delete.
        ownerRepository.deleteById(ownerId);
    }
    public Optional<Owner> findByUsername(String username) {
        return ownerRepository.findByUsername(username);
    }

}


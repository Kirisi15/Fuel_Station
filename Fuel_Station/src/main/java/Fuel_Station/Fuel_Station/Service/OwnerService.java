package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    OwnerEntity getOwnerById(Long ownerId);

    OwnerEntity createOwner(OwnerEntity ownerEntity);

    List<OwnerEntity> getAllOwners();

    OwnerEntity updateOwner(OwnerEntity ownerEntity);

    void deleteOwner(Long ownerId);

    public Optional<OwnerEntity> findByUsername(String username);

}

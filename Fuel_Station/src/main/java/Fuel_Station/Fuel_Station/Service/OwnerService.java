package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.OwnerEntity;

import java.util.List;

public interface OwnerService {
    OwnerEntity getOwnerById(Long ownerId);

    OwnerEntity createOwner(OwnerEntity ownerEntity);

    List<OwnerEntity> getAllOwners();

    OwnerEntity updateOwner(OwnerEntity ownerEntity);

    void deleteOwner(Long ownerId);
}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Owner getOwnerById(Long ownerId);

    Owner createOwner(Owner owner);

    List<Owner> getAllOwners();

    Owner updateOwner(Owner owner);

    void deleteOwner(Long ownerId);

    Optional<Owner> findByUsername(String username);


}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.dto.request.OwnerRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface OwnerService {
    Owner getById(Long id);
    ResponseEntity<?> getOwnerById(Long ownerId);

    ResponseEntity<?> createOwner(OwnerRequest owner);

    ResponseEntity<?> getAllOwners();

    ResponseEntity<?> updateOwner(Long ownerId, Owner owner);

    ResponseEntity<?> deleteOwner(Long ownerId);

    Optional<Owner> findByUsername(String username);


}

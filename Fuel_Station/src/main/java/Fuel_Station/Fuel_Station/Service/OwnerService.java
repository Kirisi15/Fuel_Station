package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import Fuel_Station.Fuel_Station.dto.request.OwnerRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface OwnerService {
    Owner getById(Long id);
    ResponseEntity<?> getOwnerById(Long ownerId);

    ResponseEntity<?> createOwner(OwnerRequest ownerRequest);

    ResponseEntity<?> getAllOwners();

    ResponseEntity<?> updateOwner(Long ownerId, OwnerRequest ownerRequest);

    ResponseEntity<?> deleteOwner(Long ownerId);
    ResponseEntity<?> login(LoginRequest loginRequest);
}

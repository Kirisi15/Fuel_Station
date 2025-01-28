package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Repository.OwnerRepository;
import Fuel_Station.Fuel_Station.dto.request.OwnerRequest;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import Fuel_Station.Fuel_Station.dto.response.OwnerResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public ResponseEntity<?> getAllOwners() {
        List<Owner> ownerList = ownerRepository.findAll();
        List<OwnerResponse> responses1 = new ArrayList<>();
        for(Owner owner: ownerList){
            OwnerResponse response = new OwnerResponse(
                    owner.getOwnerId(),
                    owner.getName(),
                    owner.getNic(),
                    owner.getContactNumber(),
                    owner.getEmail(),
                    owner.getUsername()
            );
            responses1.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "All owners fetched Successfully",
                        responses1
                )
        );
    }

    @Override
    public ResponseEntity<?> updateOwner(Long ownerId, Owner owner) {
        return null;
    }


    @Override
    public Owner getById(Long id) {
        return ownerRepository.findByOwnerId(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> getOwnerById(Long ownerId) {
        Optional<Owner> owner = ownerRepository.findById(ownerId);
        if(owner.isEmpty()) {
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "owner is not found",
                            null
                    )
            );
        }
        Owner owner1 = owner.get();
        OwnerResponse response = new OwnerResponse(
                owner1.getOwnerId(),
                owner1.getName(),
                owner1.getNic(),
                owner1.getContactNumber(),
                owner1.getEmail(),
                owner1.getUsername()
        );

        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Owner  fetched successfully",
                        response
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> createOwner(OwnerRequest ownerRequest) {
        Owner owner = new Owner(
                ownerRequest.getName(),
                ownerRequest.getNic(),
                ownerRequest.getContactNumber(),
                ownerRequest.getEmail(),
                ownerRequest.getUsername(),
                ownerRequest.getPassword()
        );
        ownerRepository.save(owner);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Owner created successfully",
                        null
                )
        );
    }



    @Transactional
    public ResponseEntity<?> updateOwner(Long ownerId, OwnerRequest ownerRequest){
        Owner existingOwner = getById(ownerId);
        if(existingOwner==null) {
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Owner not found",
                            null
                    )
            );
        }
            existingOwner.setNic(ownerRequest.getNic());
            existingOwner.setEmail(ownerRequest.getEmail());
            existingOwner.setContactNumber(ownerRequest.getContactNumber());
            ownerRepository.save(existingOwner);
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            200,
                            "Owner updated successfully",
                            null
                    )
            );
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Owner deleted successfully",
                        null
                )
        );
    }

    @Override
    public Optional<Owner> findByUsername(String username) {
        return null;
    }


}


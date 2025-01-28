package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Admin;
import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Repository.OwnerRepository;
import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import Fuel_Station.Fuel_Station.dto.request.OwnerRequest;
import Fuel_Station.Fuel_Station.dto.response.AdminResponse;
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
        if (ownerRepository.existsByEmail(ownerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Email already exist",
                            null
                    )
            );
        }
        if(ownerRepository.existsByUsername(ownerRequest.getUsername())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Username already exists",
                            null
                    )
            );
        }
        if(ownerRepository.existsByNic(ownerRequest.getNic())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "NIC already exists",
                            null
                    )
            );
        }
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
                        "Owner registered successfully",
                        null
                )
        );
    }



    @Transactional
    public ResponseEntity<?> updateOwner(Long ownerId, OwnerRequest ownerRequest){
        Owner owner = getById(ownerId);
        if(owner == null){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Owner not found with this id",
                            null
                    )
            );
        }
        if(ownerRequest.getUsername() != owner.getUsername()){
            if (ownerRepository.existsByUsername(ownerRequest.getUsername())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "Username already exist",
                                null
                        )
                );
            }else{
                owner.setUsername(ownerRequest.getUsername());
            }
        }
        if(ownerRequest.getEmail() != owner.getEmail()){
            if (ownerRepository.existsByEmail(ownerRequest.getEmail())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "Email already exist",
                                null
                        )
                );
            }else{
                owner.setEmail(ownerRequest.getEmail());
            }
        }
        if(ownerRequest.getNic() != owner.getNic()){
            if (ownerRepository.existsByNic(ownerRequest.getNic())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "NIC already exist",
                                null
                        )
                );
            }else{
                owner.setNic(ownerRequest.getNic());
            }
        }
        if(ownerRequest.getName() != owner.getName()){
            owner.setName(ownerRequest.getName());
        }
        if(ownerRequest.getContactNumber() != owner.getContactNumber()){
            owner.setContactNumber(ownerRequest.getContactNumber());
        }
        if(ownerRequest.getPassword() != owner.getPassword()){
            owner.setPassword(ownerRequest.getPassword());
        }
        ownerRepository.save(owner);
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
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<Owner> optionalOwner = ownerRepository.findByUsername(loginRequest.getUsername());
        if(optionalOwner.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Account not registered",
                            null
                    )
            );
        }
       Owner owner = optionalOwner.get();
        if(owner.getPassword() != loginRequest.getPassword()){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Password is wrong",
                            null
                    )
            );
        }
       OwnerResponse response = new OwnerResponse(
               owner.getOwnerId(),
               owner.getName(),
               owner.getNic(),
               owner.getContactNumber(),
               owner.getEmail(),
               owner.getUsername()
       );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Login successfully",
                        response
                )
        );
    }

}


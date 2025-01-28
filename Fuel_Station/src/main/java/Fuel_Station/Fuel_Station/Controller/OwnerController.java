package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Service.OwnerService;
import Fuel_Station.Fuel_Station.dto.request.CustomerRequest;
import Fuel_Station.Fuel_Station.dto.request.OwnerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/owners")
@CrossOrigin(origins = "http://localhost:3000")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<?> createOwner(@RequestBody OwnerRequest ownerRequest) {
        return ownerService.createOwner(ownerRequest);
    }


    @GetMapping("/{ownerId}")
    public ResponseEntity<?> getOwnerById(@PathVariable("ownerId") Long ownerId) {
        return ownerService.getOwnerById(ownerId);
    }

    @GetMapping
    public ResponseEntity<?> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<?> updateOwner(
            @PathVariable("ownerId") Long ownerId,
            @RequestBody Owner owner) {
        owner.setOwnerId(ownerId);
        return ownerService.updateOwner(ownerId,owner);

    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.deleteOwner(ownerId);

    }

     @PostMapping("/login")
      public ResponseEntity<?> login(@RequestBody Owner loginDetails) {

        Optional<Owner> owner = ownerService.findByUsername(loginDetails.getUsername());
        if(owner.isPresent() && owner.get().getPassword().equals(loginDetails.getPassword())){
            return new ResponseEntity<>(owner.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Invalid Username or password ",HttpStatus.UNAUTHORIZED);
        }
}

}

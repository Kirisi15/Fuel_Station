package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Owner;
import Fuel_Station.Fuel_Station.Service.OwnerService;
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
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerService.createOwner(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable("ownerId") Long ownerId) {
        Owner owner = ownerService.getOwnerById((long) ownerId);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> updateOwner(
            @PathVariable("ownerId") Long ownerId,
            @RequestBody Owner owner) {
        owner.setOwnerId(ownerId);
        Owner updatedOwner = ownerService.updateOwner(owner);
        return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<String> deleteOwner(@PathVariable("ownerId") Long ownerId) {
        ownerService.deleteOwner((long) ownerId);
        return new ResponseEntity<>("Owner successfully deleted", HttpStatus.OK);
    }
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Owner loginDetails) {
//        Optional<Owner> owner = ownerService.findByUsername(loginDetails.getUsername());
//
//        if (owner.isPresent() && owner.get().getPassword().equals(loginDetails.getPassword())) {
//            return new ResponseEntity<>(owner.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
//        }

//}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Owner loginDetails) {
    try {
        Optional<Owner> owner = ownerService.findByUsername(loginDetails.getUsername());

        if (owner.isPresent() && owner.get().getPassword().equals(loginDetails.getPassword())) {
            return new ResponseEntity<>(owner.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Logs the error to the console
        return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}

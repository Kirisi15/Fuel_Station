package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
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
    public ResponseEntity<OwnerEntity> createOwner(@RequestBody OwnerEntity ownerEntity) {
        OwnerEntity savedOwner = ownerService.createOwner(ownerEntity);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerEntity> getOwnerById(@PathVariable("id") Long ownerId) {
        OwnerEntity owner = ownerService.getOwnerById((long) ownerId);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OwnerEntity>> getAllOwners() {
        List<OwnerEntity> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerEntity> updateOwner(
            @PathVariable("id") Long ownerId,
            @RequestBody OwnerEntity ownerEntity) {
        ownerEntity.setOwnerId(ownerId);
        OwnerEntity updatedOwner = ownerService.updateOwner(ownerEntity);
        return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable("id") Long ownerId) {
        ownerService.deleteOwner((long) ownerId);
        return new ResponseEntity<>("Owner successfully deleted", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody OwnerEntity loginDetails) {
        Optional<OwnerEntity> owner = ownerService.findByUsername(loginDetails.getUsername());

        if (owner.isPresent() && owner.get().getPassword().equals(loginDetails.getPassword())) {
            return new ResponseEntity<>(owner.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

}

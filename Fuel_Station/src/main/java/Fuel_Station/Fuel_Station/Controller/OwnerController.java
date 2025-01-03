package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
import Fuel_Station.Fuel_Station.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/owners")
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
    public ResponseEntity<OwnerEntity> getOwnerById(@PathVariable("id") int ownerId) {
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
            @PathVariable("id") int ownerId,
            @RequestBody OwnerEntity ownerEntity) {
        ownerEntity.setOwnerId(ownerId);
        OwnerEntity updatedOwner = ownerService.updateOwner(ownerEntity);
        return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable("id") int ownerId) {
        ownerService.deleteOwner((long) ownerId);
        return new ResponseEntity<>("Owner successfully deleted", HttpStatus.OK);
    }
}

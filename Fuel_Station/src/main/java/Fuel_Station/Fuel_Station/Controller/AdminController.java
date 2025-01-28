package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Admin;
import Fuel_Station.Fuel_Station.Service.AdminService;
import Fuel_Station.Fuel_Station.dto.request.AdminRequest;
import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAdmin(@RequestBody AdminRequest adminRequest) {
        return adminService.createAdmin(adminRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") Long adminId, @RequestBody AdminRequest adminRequest) {
      return adminService.updateAdmin(adminId,adminRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long adminId) {
        return adminService.deleteAdmin(adminId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest loginRequest) {
        return adminService.login(loginRequest);
    }

}


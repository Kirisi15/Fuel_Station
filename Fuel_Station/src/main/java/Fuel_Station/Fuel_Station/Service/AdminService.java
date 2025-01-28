package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Admin;
import Fuel_Station.Fuel_Station.dto.request.AdminRequest;
import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    ResponseEntity<?> createAdmin(AdminRequest adminRequest);

    ResponseEntity<?> getAdminById(Long adminId);

    ResponseEntity<?> getAllAdmins();

    ResponseEntity<?> updateAdmin(Long id,AdminRequest adminRequest);

    ResponseEntity<?> deleteAdmin(Long adminId);

    ResponseEntity<?> login(LoginRequest loginRequest);
}

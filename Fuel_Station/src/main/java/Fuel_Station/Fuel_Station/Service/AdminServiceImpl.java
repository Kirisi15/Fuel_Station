package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Admin;
import Fuel_Station.Fuel_Station.Repository.AdminRepository;
import Fuel_Station.Fuel_Station.dto.request.AdminRequest;
import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import Fuel_Station.Fuel_Station.dto.response.AdminResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getById(Long adminId){
        return adminRepository.findById(adminId).orElse(null);
    }
    @Override
    public ResponseEntity<?> getAdminById(Long adminId) {
        Admin admin = getById(adminId);
        if(admin == null){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Admin found with this id",
                            null
                    )
            );
        }

        AdminResponse response = new AdminResponse(
                admin.getAdminId(),
                admin.getAdminUsername(),
                admin.getAdminEmail(),
                admin.getContactNumber()
        );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Admin fetched successfully",
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> getAllAdmins() {
        List<Admin> adminList = adminRepository.findAll();
        List<AdminResponse> responses = new ArrayList<>();
        for(Admin admin : adminList){
            AdminResponse response = new AdminResponse(
                    admin.getAdminId(),
                    admin.getAdminUsername(),
                    admin.getAdminEmail(),
                    admin.getContactNumber()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "All Admins fetched successfully",
                        responses
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateAdmin(Long id,AdminRequest adminRequest) {
        Admin admin = getById(id);
        if(admin == null){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Admin found with this id",
                            null
                    )
            );
        }
        if(adminRequest.getAdminUsername() != admin.getAdminUsername()){
            if (adminRepository.existsByAdminUsername(adminRequest.getAdminUsername())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "Username already exist",
                                null
                        )
                );
            }else{
                admin.setAdminUsername(adminRequest.getAdminUsername());
            }
        }
        if(adminRequest.getAdminEmail() != admin.getAdminEmail()){
            if (adminRepository.existsByAdminEmail(adminRequest.getAdminEmail())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "Email already exist",
                                null
                        )
                );
            }else{
                admin.setAdminEmail(adminRequest.getAdminEmail());
            }
        }
        if(adminRequest.getAdminPassword() != admin.getAdminPassword()){
            admin.setAdminPassword(adminRequest.getAdminPassword());
        }
        if(adminRequest.getContactNumber() != admin.getContactNumber()){
            admin.setContactNumber(adminRequest.getContactNumber());
        }
        adminRepository.save(admin);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Admin updated successfully",
                        null
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Admin deleted successfully",
                        null
                )
        );
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<Admin> optionalAdmin = adminRepository.findByAdminUsername(loginRequest.getUsername());
        if(optionalAdmin.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Account not registered",
                            null
                    )
            );
        }
        Admin admin = optionalAdmin.get();
        if(admin.getAdminPassword() != loginRequest.getPassword()){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Password is wrong",
                            null
                    )
            );
        }
        AdminResponse adminResponse = new AdminResponse(
                admin.getAdminId(),
                admin.getAdminUsername(),
                admin.getAdminEmail(),
                admin.getContactNumber()
        );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Login successfully",
                        adminResponse
                )
        );
    }

@Override
@Transactional
    public ResponseEntity<?> createAdmin(AdminRequest adminRequest) {
        if (adminRepository.existsByAdminEmail(adminRequest.getAdminEmail())) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Email already exist",
                            null
                    )
            );
        }
        if(adminRepository.existsByAdminUsername(adminRequest.getAdminUsername())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Username already exists",
                            null
                    )
            );
        }
        Admin admin = new Admin(
                adminRequest.getAdminUsername(),
                adminRequest.getAdminPassword(),
                adminRequest.getAdminEmail(),
                adminRequest.getContactNumber()
        );
        adminRepository.save(admin);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Admin registered successfully",
                        null
                )
        );
    }
}


package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Admin;
import Fuel_Station.Fuel_Station.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin getAdminById(Long adminId) {
        Optional<Admin> optionalAdminEntity = adminRepository.findById(adminId);
        return optionalAdminEntity.get();
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findById(admin.getAdminId()).orElse(null);

        existingAdmin.setAdminUsername(admin.getAdminUsername());
        existingAdmin.setAdminPassword(admin.getAdminPassword());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setContactNumber(admin.getContactNumber());

        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    public Optional<Admin> findByUsername(String adminUsername) {
        return adminRepository.findByAdminUsername(adminUsername);
    }

    public Admin createAdmin(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        return adminRepository.save(admin);
    }
}


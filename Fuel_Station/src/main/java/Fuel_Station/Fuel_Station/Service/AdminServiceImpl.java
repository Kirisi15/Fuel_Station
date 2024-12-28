package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
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
    public AdminEntity createAdmin(AdminEntity adminEntity) {
        return adminRepository.save(adminEntity);
    }

    @Override
    public AdminEntity getAdminById(Long adminId) {
        Optional<AdminEntity> optionalAdminEntity = adminRepository.findById(adminId);
        return optionalAdminEntity.get();
    }

    @Override
    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public AdminEntity updateAdmin(AdminEntity adminEntity) {
        AdminEntity existingAdmin = adminRepository.findById(adminEntity.getAdminId()).orElse(null);

        existingAdmin.setAdminUsername(adminEntity.getAdminUsername());
        existingAdmin.setAdminPassword(adminEntity.getAdminPassword());
        existingAdmin.setAdminEmail(adminEntity.getAdminEmail());
        existingAdmin.setContactNumber(adminEntity.getContactNumber());

        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }
}


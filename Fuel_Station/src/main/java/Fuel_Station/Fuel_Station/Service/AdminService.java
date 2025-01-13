package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    AdminEntity createAdmin(AdminEntity adminEntity);

    AdminEntity getAdminById(Long adminId);

    List<AdminEntity> getAllAdmins();

    AdminEntity updateAdmin(AdminEntity adminEntity);

    void deleteAdmin(Long adminId);

    Optional<AdminEntity> findByUsername(String adminUsername);

}

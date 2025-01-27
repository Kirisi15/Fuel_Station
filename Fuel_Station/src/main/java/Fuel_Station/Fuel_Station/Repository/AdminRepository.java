package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAdminUsername(String adminUsername);
    boolean existsByEmail(String email);
}

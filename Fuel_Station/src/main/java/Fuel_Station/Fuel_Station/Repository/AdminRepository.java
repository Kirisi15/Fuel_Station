package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByAdminUsername(String adminUsername);
}

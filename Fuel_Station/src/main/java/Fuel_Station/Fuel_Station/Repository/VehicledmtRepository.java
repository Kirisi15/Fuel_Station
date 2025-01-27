package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.Vehicledmt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicledmtRepository extends JpaRepository<Vehicledmt,String> {
    Optional<Vehicledmt> findByLicenseNumberAndNic(String licenseNumber, String nic);
}

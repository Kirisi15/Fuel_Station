package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity,Long> {
    Optional<OwnerEntity> findByUsername(String username);
}

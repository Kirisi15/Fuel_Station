package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Owner> findByUsername(String username);
    Optional<Owner> findByOwnerId(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByNic(String nic);
}

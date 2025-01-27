package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuelStationRepository extends JpaRepository <FuelStation, Long> {
    List<FuelStation> findByOwner(Owner owner);

    Optional<FuelStation> getByStationId(Long stationId);
}

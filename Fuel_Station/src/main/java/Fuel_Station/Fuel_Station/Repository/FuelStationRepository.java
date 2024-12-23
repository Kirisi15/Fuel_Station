package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelStationRepository extends JpaRepository <FuelStationEntity, Long> {
}

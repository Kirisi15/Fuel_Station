package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.FuelLimit;
import Fuel_Station.Fuel_Station.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelLimitRepository extends JpaRepository<FuelLimit, Integer> {

    Optional<FuelLimit> findByVehicleType(VehicleType vehicleType);
}


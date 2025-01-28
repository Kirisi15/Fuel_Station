package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.VehicleFuelQuota;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.Entity.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleFuelQuoteRepository extends JpaRepository<VehicleFuelQuota, Integer> {
    Optional<VehicleFuelQuota> findByVehicleAndTimePeriod(Vehicle vehicle, TimePeriod timePeriod);
}

package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

   public List<Vehicle> findByCustomer(Customer customer);
   Optional<Vehicle> getByVehicleId(Long vehicleId);

}

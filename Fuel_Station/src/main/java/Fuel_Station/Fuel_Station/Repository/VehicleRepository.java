package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

   public List<Vehicle> findByCustomer_CustomerId(Long customerId);
}

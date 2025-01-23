package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

   public List<VehicleEntity> findByCustomer_CustomerId(Long customerId);
}

package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Entity.FuelStation;

//import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
   //Optional<EmployeeEntity> findByStationId(Long stationId);

   List<Employee> findByFuelStation(FuelStation fuelStation);

}

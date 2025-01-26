package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeLimitRepository extends JpaRepository<TimePeriod, Integer> {
}

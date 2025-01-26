package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimePeriodRepository extends JpaRepository<TimePeriod, Integer> {
    TimePeriod findTopByOrderByCreatedDateDesc();
}

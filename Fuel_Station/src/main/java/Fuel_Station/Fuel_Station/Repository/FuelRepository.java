package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.FuelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract interface FuelRepository extends JpaRepository<FuelEntity, Long> {
}

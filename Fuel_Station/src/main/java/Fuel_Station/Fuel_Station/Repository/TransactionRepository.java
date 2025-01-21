package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    @Query("SELECT t FROM TransactionEntity t WHERE t.FuelStation.id = :stationId")
    List<TransactionEntity> findByStation_Id(Long stationId);
}

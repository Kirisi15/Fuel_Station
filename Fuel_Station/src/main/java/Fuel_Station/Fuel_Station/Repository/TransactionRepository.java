package Fuel_Station.Fuel_Station.Repository;

import Fuel_Station.Fuel_Station.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository <TransactionEntity, Long> {

}

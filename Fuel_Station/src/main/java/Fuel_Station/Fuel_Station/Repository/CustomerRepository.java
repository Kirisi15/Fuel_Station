package Fuel_Station.Fuel_Station.Repository;


import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
   // Optional<CustomerEntity> findById(Long customerId);
   Optional<CustomerEntity> findByCustomerUsername(String customerUsername);
}




package Fuel_Station.Fuel_Station.Repository;


import Fuel_Station.Fuel_Station.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
   // Optional<CustomerEntity> findById(Long customerId);
   Optional<Customer> findByCustomerUsername(String customerUsername);
}




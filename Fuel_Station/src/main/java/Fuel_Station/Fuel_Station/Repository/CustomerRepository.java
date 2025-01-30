package Fuel_Station.Fuel_Station.Repository;


import Fuel_Station.Fuel_Station.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
     Optional<Customer> findByCustomerUsername(String customerUsername);
    Optional<Customer> findByCustomerId(Long id);
    boolean existsByCustomerEmail(String email);
    boolean existsByCustomerUsername(String username);
    boolean existsByCustomerNIC(String nic);
}




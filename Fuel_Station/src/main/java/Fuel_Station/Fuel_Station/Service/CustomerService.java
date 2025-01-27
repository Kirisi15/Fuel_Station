package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.dto.request.CustomerRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    ResponseEntity<?> createCustomer(CustomerRequest customerRequest);

    ResponseEntity<?> getCustomerById(Long customerId);

    ResponseEntity<?> getAllCustomers();

    ResponseEntity<?> deleteCustomer(Long customerId);

    ResponseEntity<?> updateCustomer(Long customerId, CustomerRequest customerRequest);

    Optional<Customer> findByUsername(String customerEmail);
}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customerEntity);

    Customer getCustomerById(Long customerId);

    List<Customer> getAllCustomers();

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customerEntity);

    void deleteCustomer(Long customerId);

    Customer updateCustomer(Long customerId, Customer customerEntity);

  //  void deleteCustomer(Long customerId);

    Optional<Customer> findByUsername(String customerEmail);
}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerEntity createCustomer(CustomerEntity customerEntity);

    CustomerEntity getCustomerById(Long customerId);

    List<CustomerEntity> getAllCustomers();

    CustomerEntity addCustomer(CustomerEntity customer);

    CustomerEntity updateCustomer(CustomerEntity customerEntity);

    void deleteCustomer(Long customerId);

    CustomerEntity updateCustomer(Long customerId, CustomerEntity customerEntity);

  //  void deleteCustomer(Long customerId);

    Optional<CustomerEntity> findByUsername(String customerEmail);
}

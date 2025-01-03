package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerEntity createCustomer(CustomerEntity customerEntity);

    CustomerEntity getCustomerById(Integer customerId);

    List<CustomerEntity> getAllCustomers();

    CustomerEntity addCustomer(CustomerEntity customer);

    CustomerEntity updateCustomer(CustomerEntity customerEntity);

    void deleteCustomer(Integer customerId);

    CustomerEntity updateCustomer(Long customerId, CustomerEntity customerEntity);

    void deleteCustomer(Long customerId);
}

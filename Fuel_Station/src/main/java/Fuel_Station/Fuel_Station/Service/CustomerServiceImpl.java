package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.CustomerEntity;
import Fuel_Station.Fuel_Station.Repository.CustomerReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    final private CustomerReposiory customerReposiory;


    public CustomerServiceImpl(CustomerReposiory customerReposiory) {
        this.customerReposiory = customerReposiory;
    }

    @Override
    public CustomerEntity createCustomer(CustomerEntity customerEntity) {
        return customerReposiory.save(customerEntity);
    }


    @Override
    public CustomerEntity getCustomerById(Integer customerId) {
        Optional<CustomerEntity> customer = customerReposiory.findById(customerId);
        return customer.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerReposiory.findAll();
    }

    @Override
    public CustomerEntity addCustomer(CustomerEntity customer) {
        return customerReposiory.save(customer);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        CustomerEntity existingCustomer = customerReposiory.findById(customerEntity.getCustomerId()).orElse(null);
        existingCustomer.setCustomerNIC(customerEntity.getCustomerNIC());
        existingCustomer.setCustomerName(customerEntity.getCustomerName());
        existingCustomer.setCustomerEmail(customerEntity.getCustomerEmail());
        existingCustomer.setCustomerUsername(customerEntity.getCustomerUsername());
        existingCustomer.setCustomerPassword(customerEntity.getCustomerPassword());

        return customerReposiory.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerReposiory.deleteById(customerId);
    }
}

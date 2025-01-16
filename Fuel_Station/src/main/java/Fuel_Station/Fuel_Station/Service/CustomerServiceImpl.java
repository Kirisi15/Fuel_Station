package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.CustomerEntity;
import Fuel_Station.Fuel_Station.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{


     private final CustomerRepository customerRepository;

    @Autowired
   public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity createCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }


    @Override
    public CustomerEntity getCustomerById(Integer customerId) {
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        return customer.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity addCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        CustomerEntity existingCustomer = customerRepository.findById(customerEntity.getCustomerId()).orElse(null);
        existingCustomer.setCustomerNIC(customerEntity.getCustomerNIC());
        existingCustomer.setCustomerName(customerEntity.getCustomerName());
        existingCustomer.setCustomerEmail(customerEntity.getCustomerEmail());
        existingCustomer.setCustomerUsername(customerEntity.getCustomerUsername());
        existingCustomer.setCustomerPassword(customerEntity.getCustomerPassword());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerEntity updateCustomer(Long customerId, CustomerEntity customerEntity) {
        return null;
    }

    @Override
   public void deleteCustomer(Long customerId) {

   }
    public Optional<CustomerEntity> findByUsername(String customerUsername) {
        return customerRepository.findByCustomerUsername(customerUsername);
    }
}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Customer;
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
    public Customer createCustomer(Customer customerEntity) {
        return customerRepository.save(customerEntity);
    }


    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customerEntity) {
        Customer existingCustomer = customerRepository.findById(customerEntity.getCustomerId()).orElse(null);
        existingCustomer.setCustomerNIC(customerEntity.getCustomerNIC());
        existingCustomer.setCustomerName(customerEntity.getCustomerName());
        existingCustomer.setCustomerEmail(customerEntity.getCustomerEmail());
        existingCustomer.setCustomerUsername(customerEntity.getCustomerUsername());
        existingCustomer.setCustomerPassword(customerEntity.getCustomerPassword());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerEntity) {
        return null;
    }

    @Override
//   public void deleteCustomer(Long customerId) {
//
//   }
    public Optional<Customer> findByUsername(String customerUsername) {
        return customerRepository.findByCustomerUsername(customerUsername);
    }
}

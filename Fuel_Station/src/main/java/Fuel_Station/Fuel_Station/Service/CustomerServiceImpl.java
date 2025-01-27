package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Repository.CustomerRepository;
import Fuel_Station.Fuel_Station.dto.request.CustomerRequest;
import Fuel_Station.Fuel_Station.dto.response.CustomerResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Transactional
    public ResponseEntity<?> createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer(
                customerRequest.getCustomerNIC(),
                customerRequest.getCustomerName(),
                customerRequest.getCustomerEmail(),
                customerRequest.getCustomerUsername(),
                customerRequest.getCustomerPassword()
        );
        customerRepository.save(customer);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Customer created successfully",
                        null
                )
        );
    }


    @Override
    public ResponseEntity<?> getCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Customer not find for this id",
                            null
                    )
            );
        }
        Customer customer = optionalCustomer.get();
        CustomerResponse response = new CustomerResponse(
                customer.getCustomerId(),
                customer.getCustomerNIC(),
                customer.getCustomerName(),
                customer.getCustomerEmail(),
                customer.getCustomerUsername()
        );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Customer fetched successfully",
                        response
                )
        );
    }

    @Override
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> responses = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse response = new CustomerResponse(
                    customer.getCustomerId(),
                    customer.getCustomerNIC(),
                    customer.getCustomerName(),
                    customer.getCustomerEmail(),
                    customer.getCustomerUsername()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Customers fetched successfully",
                        responses
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateCustomer(Long id, CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Customer not find for this id",
                            null
                    )
            );
        }
        Customer existingCustomer = optionalCustomer.get();
        existingCustomer.setCustomerNIC(customerRequest.getCustomerNIC());
        existingCustomer.setCustomerName(customerRequest.getCustomerName());
        existingCustomer.setCustomerEmail(customerRequest.getCustomerEmail());
        existingCustomer.setCustomerUsername(customerRequest.getCustomerUsername());
        existingCustomer.setCustomerPassword(customerRequest.getCustomerPassword());

        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Customer updated successfully",
                        null
                )
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Customer deleted successfully",
                        null
                )
        );
    }

    public Optional<Customer> findByUsername(String customerUsername) {
        return customerRepository.findByCustomerUsername(customerUsername);
    }
}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Admin;
import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Repository.CustomerRepository;
import Fuel_Station.Fuel_Station.dto.request.CustomerRequest;
import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import Fuel_Station.Fuel_Station.dto.response.AdminResponse;
import Fuel_Station.Fuel_Station.dto.response.CustomerResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{


     private final CustomerRepository customerRepository;

    @Autowired
   public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findByCustomerId(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<?> createCustomer(CustomerRequest customerRequest) {
        if (customerRepository.existsByCustomerEmail(customerRequest.getCustomerEmail())) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Email already exist",
                            null
                    )
            );
        }
        if(customerRepository.existsByCustomerUsername(customerRequest.getCustomerUsername())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Username already exists",
                            null
                    )
            );
        }
        if(customerRepository.existsByCustomerNIC(customerRequest.getCustomerNIC())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "NIC already exists",
                            null
                    )
            );
        }
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
                        "Customer registered successfully",
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
        Customer customer = getById(id);
        if(customer == null){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Customer not found with this id",
                            null
                    )
            );
        }
        if(customerRequest.getCustomerUsername() != customer.getCustomerUsername()){
            if (customerRepository.existsByCustomerUsername(customerRequest.getCustomerUsername())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "Username already exist",
                                null
                        )
                );
            }else{
                customer.setCustomerUsername(customerRequest.getCustomerUsername());
            }
        }
        if(customerRequest.getCustomerEmail() != customer.getCustomerEmail()){
            if (customerRepository.existsByCustomerEmail(customerRequest.getCustomerEmail())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "Email already exist",
                                null
                        )
                );
            }else{
                customer.setCustomerEmail(customerRequest.getCustomerEmail());
            }
        }
        if(customerRequest.getCustomerNIC() != customer.getCustomerNIC()){
            if (customerRepository.existsByCustomerNIC(customerRequest.getCustomerNIC())) {
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "NIC already exist",
                                null
                        )
                );
            }else{
                customer.setCustomerNIC(customerRequest.getCustomerNIC());
            }
        }
        if(customerRequest.getCustomerPassword() != customer.getCustomerPassword()){
            customer.setCustomerPassword(customerRequest.getCustomerPassword());
        }
        if(customerRequest.getCustomerName() != customer.getCustomerName()){
            customer.setCustomerName(customerRequest.getCustomerName());
        }
        customerRepository.save(customer);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Customer updated successfully",
                        null
                )
        );
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerUsername(loginRequest.getUsername());
        if(optionalCustomer.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Account not registered",
                            null
                    )
            );
        }
        Customer customer = optionalCustomer.get();
        if(!customer.getCustomerPassword().equals( loginRequest.getPassword())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Password is wrong",
                            null
                    )
            );
        }
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
                        "Login successfully",
                        response
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
}

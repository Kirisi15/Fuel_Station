package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Service.CustomerService;
import Fuel_Station.Fuel_Station.dto.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://Localhost:3000/")
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(customerId, customerRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long customerId) {
        return  customerService.deleteCustomer(customerId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer loginDetails) {
        Optional<Customer> customer= customerService.findByUsername(loginDetails.getCustomerUsername());

        if (customer.isPresent() && customer.get().getCustomerPassword().equals(loginDetails.getCustomerPassword())) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}

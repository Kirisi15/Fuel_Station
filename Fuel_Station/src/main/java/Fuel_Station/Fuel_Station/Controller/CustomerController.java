package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.CustomerEntity;
import Fuel_Station.Fuel_Station.Service.CustomerService;
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
    public List<CustomerEntity> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerEntity getCustomerById(@PathVariable("id") Long customerId) {
        return customerService.getCustomerById(Math.toIntExact(customerId));
    }

    @PostMapping
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customerEntity) {
        return customerService.createCustomer(customerEntity);
    }

    @PutMapping("/{id}")
    public CustomerEntity updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerEntity customerEntity) {
        return customerService.updateCustomer(customerId, customerEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody CustomerEntity loginDetails) {
        Optional<CustomerEntity> customer= customerService.findByUsername(loginDetails.getCustomerUsername());

        if (customer.isPresent() && customer.get().getCustomerPassword().equals(loginDetails.getCustomerPassword())) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}

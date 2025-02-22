package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Service.EmployeeService;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import Fuel_Station.Fuel_Station.dto.request.EmployeeRequest;

import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:3000"}, allowCredentials = "true")
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FuelStationService fuelStationService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Long employeeId)
    {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
        return employeeService.createEmployee(employeeRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        return employeeService.login(loginRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeRequest employeeRequest)
    {
        return employeeService.updateEmployee(employeeId,employeeRequest);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId)
    {
        return  employeeService.deleteEmployee(employeeId);
    }
    @GetMapping("/{stationId}")
    public ResponseEntity<?> getEmployeeByStationId(@PathVariable("stationId") Long stationId)
    {
        return employeeService.getEmployeeByStationId(stationId);
    }
}

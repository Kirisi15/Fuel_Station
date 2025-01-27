package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Service.EmployeeService;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import Fuel_Station.Fuel_Station.dto.request.EmployeeRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://Localhost:3000/")
@RequestMapping("/employee")
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

    @PostMapping("/{stationId}")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employee,@PathVariable Long stationId)
    {
        return employeeService.createEmployee(employee,stationId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeRequest employee)
    {
        return employeeService.updateEmployee(employeeId,employee);
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

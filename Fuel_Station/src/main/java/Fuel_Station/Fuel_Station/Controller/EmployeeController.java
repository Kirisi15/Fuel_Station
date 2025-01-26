package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Service.EmployeeService;
import Fuel_Station.Fuel_Station.Service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Employee> getEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId)
    {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/{stationId}")
    public Employee createEmployee(@RequestBody Employee employeeEntity,@PathVariable Long stationId)
    {
        return employeeService.createEmployee(employeeEntity,stationId);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee)
    {
        return employeeService.updateEmployee(employeeId,employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
    }
    @GetMapping("/{stationId}")
    public Employee getEmployeeByStationId(@PathVariable("stationId") Long stationId)
    {
        return employeeService.getEmployeeByStationId(stationId);
    }
}

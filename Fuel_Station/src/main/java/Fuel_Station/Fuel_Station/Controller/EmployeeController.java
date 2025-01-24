package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.EmployeeEntity;
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
    public List<EmployeeEntity> getEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable("employeeId") Long employeeId)
    {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/{stationId}")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity,@PathVariable Long stationId)
    {
        return employeeService.createEmployee(employeeEntity,stationId);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeEntity employeeEntity)
    {
        return employeeService.updateEmployee(employeeId,employeeEntity);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
    }
    @GetMapping("/{stationId}")
    public EmployeeEntity getEmployeeByStationId(@PathVariable("stationId") Long stationId)
    {
        return employeeService.getEmployeeByStationId(stationId);
    }
}

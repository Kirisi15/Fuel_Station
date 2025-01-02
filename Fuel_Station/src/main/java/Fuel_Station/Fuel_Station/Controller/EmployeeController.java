package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.EmployeeEntity;
import Fuel_Station.Fuel_Station.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeEntity> getEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable("id") Long Employee_Id)
    {
        return employeeService.getEmployeeById(Employee_Id);
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity)
    {
        return employeeService.createEmployee(employeeEntity);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable("id") Long Employee_Id, @RequestBody EmployeeEntity employeeEntity)
    {
        return employeeService.updateEmployee(Employee_Id,employeeEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long Employee_Id)
    {
        employeeService.deleteEmployee(Employee_Id);
    }
}

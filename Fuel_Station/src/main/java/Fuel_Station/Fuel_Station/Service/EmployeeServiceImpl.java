package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Repository.EmployeeRepository;
import Fuel_Station.Fuel_Station.Repository.FuelStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
  private EmployeeRepository employeeRepository;
    @Autowired
    private FuelStationRepository fuelStationRepository;
   public Employee createEmployee(Employee employee, Long stationId )
   {
       FuelStation fuelStation= fuelStationRepository.findById(stationId).get();
       employee.setFuelStation(fuelStation);
       return employeeRepository.save(employee);
   }
  public Employee getEmployeeById(Long employeeId)
    {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee.get();
    }
   public List<Employee> getAllEmployees()
   {
       return employeeRepository.findAll();
   }
    public Employee updateEmployee(Long employee_Id, Employee Employee)
    {
        Employee existingEmployee = getEmployeeById(employee_Id);
        existingEmployee.setEmployeeContactnumber(Employee.getEmployeeContactnumber());
        existingEmployee.setEmployeeName(Employee.getEmployeeName());
        existingEmployee.setEmployeeNic(Employee.getEmployeeNic());
        existingEmployee.setEmployeePassword(Employee.getEmployeePassword());
        existingEmployee.setEmployeeUsername(Employee.getEmployeeUsername());

        return employeeRepository.save(existingEmployee);
    }
  public  void deleteEmployee(Long employeeId)
  {
     employeeRepository.deleteById(employeeId);
  }

}

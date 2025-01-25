package Fuel_Station.Fuel_Station.Service;



import Fuel_Station.Fuel_Station.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee, Long stationId);

    Employee getEmployeeById(Long EmployeeId);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long employeeId, Employee Employee);

    void deleteEmployee(Long EmployeeId);

}

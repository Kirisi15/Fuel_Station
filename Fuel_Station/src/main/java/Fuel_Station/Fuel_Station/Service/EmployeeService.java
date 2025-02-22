package Fuel_Station.Fuel_Station.Service;



import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.dto.request.EmployeeRequest;

import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    Employee getById(Long id);
    ResponseEntity<?> createEmployee(EmployeeRequest employee);
    ResponseEntity<?> getEmployeeById(Long EmployeeId);
    ResponseEntity<?> getAllEmployees();
    ResponseEntity<?> updateEmployee(Long employeeId, EmployeeRequest Employee);
    ResponseEntity<?> getEmployeeByStationId(Long EmployeeId);
    ResponseEntity<?> deleteEmployee(Long EmployeeId);

    ResponseEntity<?> login(LoginRequest loginRequest);
}

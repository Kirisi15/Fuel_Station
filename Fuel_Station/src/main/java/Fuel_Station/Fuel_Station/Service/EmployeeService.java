package Fuel_Station.Fuel_Station.Service;



import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.dto.request.EmployeeRequest;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<?> createEmployee(EmployeeRequest employee, Long stationId);

    ResponseEntity<?> getEmployeeById(Long EmployeeId);

    ResponseEntity<?> getAllEmployees();

    ResponseEntity<?> updateEmployee(Long employeeId, EmployeeRequest Employee);
    ResponseEntity<?> getEmployeeByStationId(Long EmployeeId);

    ResponseEntity<?> deleteEmployee(Long EmployeeId);

}

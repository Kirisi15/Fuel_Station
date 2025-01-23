package Fuel_Station.Fuel_Station.Service;



import Fuel_Station.Fuel_Station.Entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeEntity createEmployee(EmployeeEntity employeeEntity,Long stationId);

    EmployeeEntity getEmployeeById(Long EmployeeId);

    List< EmployeeEntity> getAllEmployees();

    EmployeeEntity updateEmployee(Long employeeId, EmployeeEntity EmployeeEntity);

    void deleteEmployee(Long EmployeeId);

}

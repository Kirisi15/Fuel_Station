package Fuel_Station.Fuel_Station.Service;



import Fuel_Station.Fuel_Station.Entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeEntity createEmployee(EmployeeEntity employeeEntity);

    EmployeeEntity getEmployeeById(Long Employee_Id);

    List< EmployeeEntity> getAllEmployees();

    EmployeeEntity updateEmployee(Long employee_Id, EmployeeEntity EmployeeEntity);

    void deleteEmployee(Long Employee_Id);

}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Entity.FuelStation;

import Fuel_Station.Fuel_Station.Repository.EmployeeRepository;

import Fuel_Station.Fuel_Station.dto.request.EmployeeRequest;
import Fuel_Station.Fuel_Station.dto.response.EmployeeResponse;
//import Fuel_Station.Fuel_Station.dto.response.FuelStationResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
  private EmployeeRepository employeeRepository;
    @Autowired
    private FuelStationService fuelStationService;
   
   
    public ResponseEntity<?> createEmployee(EmployeeRequest employeeRequest, Long stationId )
   {
       FuelStation fuelStation= fuelStationService.getById(stationId);
       Employee employee = new Employee(
        fuelStation,
        employeeRequest.getEmployeeNic(),
        employeeRequest.getEmployeeName(),
        employeeRequest.getEmployeeContactnumber(),
        employeeRequest.getEmployeeUsername(),
       employeeRequest.getEmployeePassword()
       );

      employeeRepository.save(employee);
      return ResponseEntity.ok().body(
            new MessageResponse<>(
                status : 200,
                message : "employee added successfully",
                data : null

        ))  ;    
   }
  public ResponseEntity<?> getEmployeeById(Long employeeId)
    {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if( optionalEmployee.isEmpty()){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "employee id not found",
                            null
                    )
            );
        }
         Employee employee = optionalEmployee.get();
    EmployeeResponse response = new EmployeeResponse(
                employee.getEmployeeNic(),
                employee.getEmployeeContactnumber(),
                employee.getEmployeeName(),
                employee.getEmployeeUsername(),
                employee.getEmployeePassword(),
                employee.getEmployeeId()
        );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "employee fetched successfully",
                        response
                )
        );
    }
   public ResponseEntity<?> getAllEmployees()
   {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponse> responses = new ArrayList<>();
        for(Employee employee:employeeList){
            EmployeeResponse response = new EmployeeResponse(
                    employee.getEmployeeNic(),
                    employee.getEmployeeContactnumber(),
                    employee.getEmployeeName(),
                    employee.getEmployeeUsername(),
                    employee.getEmployeePassword(),
                    employee.getEmployeeId()
            );
            responses.add(response);
        }
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "All employee fetched successfully",
                        responses
                )
        );
   }
   
   
   
   public ResponseEntity<?> updateEmployee(Long employeeId, EmployeeRequest employee)
    { Employee existingEmployee = getById(employeeId);
                if(existingEmployee == null){
                    return ResponseEntity.ok().body(
                            new MessageResponse<>(
                                    400,
                                    "Employee not found",
                                    null
                            )
                    );
                }
                existingEmployee.setEmployeeNic(employee.getEmployeeNic());
                existingEmployee.setEmployeeName(employee.getEmployeeName());
                existingEmployee.setEmployeeContactnumber(employee.getEmployeeContactnumber());
                existingEmployee.setEmployeeUsername(employee.getEmployeeUsername());
                existingEmployee.setEmployeePassword(employee.getEmployeePassword());
               employeeRepository.save(existingEmployee);
                return ResponseEntity.ok().body(
                        new MessageResponse<>(
                                200,
                                "employee updated successfully",
                                null
                        )
                );
            }
          
       
    
    
    public  ResponseEntity<?> deleteEmployee(Long employeeId)
  {
    employeeRepository.deleteById(employeeId);
    return ResponseEntity.ok().body(
            new MessageResponse<>(
                    200,
                    "Employee deleted successfully",
                    null
            )
    );
  }

public ResponseEntity<?> getEmployeeByStationId(Long stationId) {
    FuelStation fuelStation =fuelStationService.getById(stationId);
        if(fuelStation != null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "station not found",
                            null
                    )
            );
        }
       List<Employee> employeeList =  employeeRepository.findByFuelStation(fuelStation);
       List<EmployeeResponse> responses = new ArrayList<>();
       for(Employee employee:employeeList){
           EmployeeResponse response = new EmployeeResponse(
                        employee.getEmployeeId(),
                  employee.getEmployeeNic(),
                  employee.getEmployeeName(),
                  employee.getEmployeeContactnumber(),
                  employee.getEmployeeUsername()
           );
           responses.add(response);
       }
       return ResponseEntity.ok().body(
               new MessageResponse<>(
                       200,
                       "All employee fetched successfully",
                       responses
               )
       );
}
}

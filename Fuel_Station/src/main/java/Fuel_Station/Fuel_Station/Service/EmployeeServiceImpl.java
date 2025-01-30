package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Customer;
import Fuel_Station.Fuel_Station.Entity.Employee;
import Fuel_Station.Fuel_Station.Entity.FuelStation;

import Fuel_Station.Fuel_Station.Repository.EmployeeRepository;

import Fuel_Station.Fuel_Station.dto.request.EmployeeRequest;
import Fuel_Station.Fuel_Station.dto.request.LoginRequest;
import Fuel_Station.Fuel_Station.dto.response.CustomerResponse;
import Fuel_Station.Fuel_Station.dto.response.EmployeeResponse;
//import Fuel_Station.Fuel_Station.dto.response.FuelStationResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;

import jakarta.transaction.Transactional;
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
    public Employee getById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<?> createEmployee(EmployeeRequest employeeRequest) {
       FuelStation fuelStation= fuelStationService.getById(employeeRequest.getStationId());
       if(fuelStation == null){
           return ResponseEntity.badRequest().body(
                   new MessageResponse<>(
                           400,
                           "Fuel station not found for this id",
                           null
                   )
           );
       }
       if(employeeRepository.existsByEmployeeNic(employeeRequest.getEmployeeNic())){
           return ResponseEntity.badRequest().body(
                   new MessageResponse<>(
                           400,
                           "NIC already exist",
                           null
                   )
           );
       }
       if(employeeRepository.existsByEmployeeUsername(employeeRequest.getEmployeeUsername())){
           return ResponseEntity.badRequest().body(
                   new MessageResponse<>(
                           400,
                           "Username already exist",
                           null
                   )
           );
       }
       Employee employee = new Employee(
               employeeRequest.getEmployeeNic(),
               employeeRequest.getEmployeeName(),
               employeeRequest.getEmployeeContactnumber(),
               employeeRequest.getEmployeeUsername(),
               employeeRequest.getEmployeePassword(),
               fuelStation
       );
      employeeRepository.save(employee);
      return ResponseEntity.ok().body(
            new MessageResponse<>(
                200,
                "employee added successfully",
                null
            )
      );
   }
  public ResponseEntity<?> getEmployeeById(Long employeeId) {
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
               employee.getEmployeeId(),
               employee.getEmployeeNic(),
               employee.getEmployeeContactnumber(),
               employee.getEmployeeName(),
               employee.getEmployeeUsername()
       );
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "employee fetched successfully",
                        response
                )
        );
    }
   public ResponseEntity<?> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponse> responses = new ArrayList<>();
        for(Employee employee:employeeList){
            EmployeeResponse response = new EmployeeResponse(
                    employee.getEmployeeId(),
                    employee.getEmployeeNic(),
                    employee.getEmployeeContactnumber(),
                    employee.getEmployeeName(),
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


   @Transactional
   public ResponseEntity<?> updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        Employee employee = getById(employeeId);
        if(employee == null){
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Employee not found for given id",
                            null
                    )
            );
        }
        if(employee.getEmployeeNic() != employeeRequest.getEmployeeNic()){
            if(employeeRepository.existsByEmployeeNic(employeeRequest.getEmployeeNic())){
                return ResponseEntity.badRequest().body(
                        new MessageResponse<>(
                                400,
                                "NIC already exist",
                                null
                        )
                );
            }else{
                employee.setEmployeeNic(employeeRequest.getEmployeeNic());
            }
        }
       if(employee.getEmployeeUsername() != employeeRequest.getEmployeeUsername()){
           if(employeeRepository.existsByEmployeeUsername(employeeRequest.getEmployeeUsername())){
               return ResponseEntity.badRequest().body(
                       new MessageResponse<>(
                               400,
                               "Username already exist",
                               null
                       )
               );
           }else{
               employee.setEmployeeUsername(employeeRequest.getEmployeeUsername());
           }
       }
       if(employee.getEmployeeName() != employeeRequest.getEmployeeName()){
           employee.setEmployeeName(employeeRequest.getEmployeeName());
       }
       if(employee.getEmployeeContactnumber() != employeeRequest.getEmployeeContactnumber()){
           employee.setEmployeeContactnumber(employeeRequest.getEmployeeContactnumber());
       }
       if(employee.getEmployeePassword() != employeeRequest.getEmployeePassword()){
           employee.setEmployeePassword(employeeRequest.getEmployeePassword());
       }
       employeeRepository.save(employee);
       return ResponseEntity.ok().body(
               new MessageResponse<>(
                       200,
                       "employee updated successfully",
                       null
               )
       );
    }

    @Transactional
    public  ResponseEntity<?> deleteEmployee(Long employeeId) {
    Optional<Employee> employee = employeeRepository.findById(employeeId);
    if(employee.isEmpty()){
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        400,
                        "Employee not Found",
                        null
                )
        );
    }
    employeeRepository.deleteById(employeeId);
    return ResponseEntity.ok().body(
            new MessageResponse<>(
                    200,
                    "Employee deleted successfully",
                    null
            )
    );
  }

    @Transactional
    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeUsername(loginRequest.getUsername());
        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Account not registered",
                            null
                    )
            );
        }
        Employee employee = optionalEmployee.get();

        // FIX: Use .equals() for string comparison to check if the passwords match
        if (!employee.getEmployeePassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse<>(
                            400,
                            "Password is incorrect",
                            null
                    )
            );
        }

        EmployeeResponse response = new EmployeeResponse(
                employee.getEmployeeId(),
                employee.getEmployeeNic(),
                employee.getEmployeeName(),
                employee.getEmployeeContactnumber(),
                employee.getEmployeeUsername()
        );

        // Return successful response with employee details if login is successful
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Login successful",
                        response
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

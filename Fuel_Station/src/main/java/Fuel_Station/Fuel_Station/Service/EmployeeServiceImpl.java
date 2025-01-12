package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.EmployeeEntity;
import Fuel_Station.Fuel_Station.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
  private EmployeeRepository employeeRepository;
   public  EmployeeEntity createEmployee(EmployeeEntity employeeEntity)
   {
       return employeeRepository.save(employeeEntity);
   }
  public  EmployeeEntity getEmployeeById(Long employeeId)
    {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee.get();
    }
   public List< EmployeeEntity> getAllEmployees()
   {
       return employeeRepository.findAll();
   }
    public EmployeeEntity updateEmployee( Long employee_Id,EmployeeEntity EmployeeEntity)
    {
        EmployeeEntity existingEmployee = getEmployeeById(employee_Id);
        existingEmployee.setEmployeeContactnumber(EmployeeEntity.getEmployeeContactnumber());
        existingEmployee.setEmployeeJobrole(EmployeeEntity.getEmployeeJobrole());
        existingEmployee.setEmployeeNic(EmployeeEntity.getEmployeeNic());
        existingEmployee.setEmployeePassword(EmployeeEntity.getEmployeePassword());
        existingEmployee.setEmployeeUsername(EmployeeEntity.getEmployeeUsername());

        return employeeRepository.save(existingEmployee);
    }
  public  void deleteEmployee(Long employeeId)
  {
     employeeRepository.deleteById(employeeId);
  }

}

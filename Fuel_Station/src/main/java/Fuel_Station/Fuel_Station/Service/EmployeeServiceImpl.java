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
  public  EmployeeEntity getEmployeeById(Long employee_Id)
    {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employee_Id);
        return optionalEmployee.get();
    }
   public List< EmployeeEntity> getAllEmployees()
   {
       return employeeRepository.findAll();
   }
    public EmployeeEntity updateEmployee( Long employee_Id,EmployeeEntity EmployeeEntity)
    {
        EmployeeEntity existingEmployee = getEmployeeById(employee_Id);
        existingEmployee.setEmployee_Contactnumber(EmployeeEntity.getEmployee_Contactnumber());
        existingEmployee.setEmployee_Jobrole(EmployeeEntity.getEmployee_Jobrole());
        existingEmployee.setEmployee_NIC(EmployeeEntity.getEmployee_NIC());
        existingEmployee.setEmployee_Password(EmployeeEntity.getEmployee_Password());
        existingEmployee.setEmployee_Username(EmployeeEntity.getEmployee_Username());

        return employeeRepository.save(existingEmployee);
    }
  public  void deleteEmployee(Long employee_Id)
  {
     employeeRepository.deleteById(employee_Id);
  }

}

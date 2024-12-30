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
  public  EmployeeEntity getEmployeeById(Long Employee_Id)
    {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(Employee_Id);
        return optionalEmployee.get();
    }
   public List< EmployeeEntity> getAllEmployees()
   {
       return employeeRepository.findAll();
   }
    public EmployeeEntity updateEmployee( EmployeeEntity EmployeeEntity)
    {
        EmployeeEntity existingEmployee = employeeRepository.findById(EmployeeEntity.getEmployee_Id()).orElse(null);
        existingEmployee.setEmployee_Contactnumber(EmployeeEntity.getEmployee_Contactnumber());
        existingEmployee.setEmployee_Jobrole(EmployeeEntity.getEmployee_Jobrole());
        existingEmployee.setEmployee_NIC(EmployeeEntity.getEmployee_NIC());
        existingEmployee.setEmployee_Password(EmployeeEntity.getEmployee_Password());
        existingEmployee.setEmployee_Username(EmployeeEntity.getEmployee_Username());

        return employeeRepository.save(existingEmployee);
    }
  public  void deleteEmployee(Long Employee_Id)
  {
     employeeRepository.deleteById(Employee_Id);
  }

}

package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.AdminEntity;
import Fuel_Station.Fuel_Station.Entity.EmployeeEntity;
import Fuel_Station.Fuel_Station.Entity.FuelStationEntity;
import Fuel_Station.Fuel_Station.Entity.OwnerEntity;
import Fuel_Station.Fuel_Station.Repository.EmployeeRepository;
import Fuel_Station.Fuel_Station.Repository.FuelStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
  private EmployeeRepository employeeRepository;
    @Autowired
    private FuelStationRepository fuelStationRepository;
   public  EmployeeEntity createEmployee(EmployeeEntity employeeEntity,Long stationId )
   {
       FuelStationEntity fuelStation= fuelStationRepository.findById(stationId).get();
       employeeEntity.setFuelStation(fuelStation);
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
        existingEmployee.setEmployeeName(EmployeeEntity.getEmployeeName());
        existingEmployee.setEmployeeNic(EmployeeEntity.getEmployeeNic());
        existingEmployee.setEmployeePassword(EmployeeEntity.getEmployeePassword());
        existingEmployee.setEmployeeUsername(EmployeeEntity.getEmployeeUsername());

        return employeeRepository.save(existingEmployee);
    }
  public  void deleteEmployee(Long employeeId)
  {
     employeeRepository.deleteById(employeeId);
  }
//    public  EmployeeEntity getEmployeeByStationId(Long stationId)
//    {
//        Optional<EmployeeEntity> employee = employeeRepository.findByStationId(stationId);
//        return employee.get();
//    }
public EmployeeEntity getEmployeeByStationId(Long stationId) {
    Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(stationId);
    return optionalEmployee.get(); // Return null if no employee is found
}
}

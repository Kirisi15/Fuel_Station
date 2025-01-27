package Fuel_Station.Fuel_Station.dto.request;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import lombok.Data;

@Data
public class EmployeeRequest {
    private Long employeeNic;
    private String employeeName;
    private String employeeContactnumber;
    private String employeeUsername;
    private String employeePassword;
    private FuelStation fuelStation;


}

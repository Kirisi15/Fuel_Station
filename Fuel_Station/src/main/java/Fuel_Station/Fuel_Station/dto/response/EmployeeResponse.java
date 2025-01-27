package Fuel_Station.Fuel_Station.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private Long employeeId;
    private Long employeeNic;
    private String employeeName;
    private String employeeContactnumber;
    private String employeeUsername;

}

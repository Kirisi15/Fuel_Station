package Fuel_Station.Fuel_Station.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponse {

    private Long customerId;

    private String customerNIC;

    private String customerName;

    private  String customerEmail;

    private String customerUsername;


}

package Fuel_Station.Fuel_Station.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerRequest {
    private String customerNIC;
    private String customerName;
    private  String customerEmail;
    private String customerUsername;
    private String customerPassword;
}

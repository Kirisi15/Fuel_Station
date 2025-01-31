package Fuel_Station.Fuel_Station.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AdminRequest {
    private String adminUsername;
    private String adminPassword;
    private String adminEmail;
    private String contactNumber;


}


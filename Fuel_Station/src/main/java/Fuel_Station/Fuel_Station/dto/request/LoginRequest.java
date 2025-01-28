package Fuel_Station.Fuel_Station.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String adminUsername;
    private String adminPassword;
}

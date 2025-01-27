package Fuel_Station.Fuel_Station.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OwnerRequest {

    private String name;

    private String nic;

    private String contactNumber;

    private String email;

    private String username;

    private String password;
}

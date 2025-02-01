package Fuel_Station.Fuel_Station.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AdminResponse {
    private Long adminId;
    private String adminUsername;
    private String adminEmail;
    private String contactNumber;
}

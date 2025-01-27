package Fuel_Station.Fuel_Station.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AdminResponse {
    private long adminId;

    @Column(nullable = false)
    private String adminUsername;


    @Column(nullable = false)
    private String adminEmail;

    @Column
    private String contactNumber;

}

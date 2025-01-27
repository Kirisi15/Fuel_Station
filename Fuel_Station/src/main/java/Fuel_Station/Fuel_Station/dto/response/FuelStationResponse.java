package Fuel_Station.Fuel_Station.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuelStationResponse {
    private Long stationId;
    private String stationName;
    private String address;
    private String licenseNumber;
    private String contactNumber;
}

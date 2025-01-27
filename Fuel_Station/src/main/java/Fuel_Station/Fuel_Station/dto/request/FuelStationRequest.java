package Fuel_Station.Fuel_Station.dto.request;

import lombok.Data;

@Data
public class FuelStationRequest {
    private String stationName;
    private String address;
    private String licenseNumber;
    private String contactNumber;
}

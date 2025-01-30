package Fuel_Station.Fuel_Station.dto.response;

import Fuel_Station.Fuel_Station.Entity.TimePeriod;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleFuelQuotaResponse {
    private int id;
    private Vehicle vehicle;
    private TimePeriod timePeriod;
    private int pumpedFuel;
}

package Fuel_Station.Fuel_Station.dto.request;

import Fuel_Station.Fuel_Station.Entity.TimePeriod;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class VehicleFuelQuotaRequest {
    private Vehicle vehicle;
    private TimePeriod timePeriod;
    private int pumpedFuel;
}

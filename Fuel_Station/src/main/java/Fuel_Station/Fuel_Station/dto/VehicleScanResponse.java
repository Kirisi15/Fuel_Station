package Fuel_Station.Fuel_Station.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class VehicleScanResponse {
    private int pumbed;
    private int fuelLimit;

    public int getPumbed() {
        return pumbed;
    }

    public void setPumbed(int pumbed) {
        this.pumbed = pumbed;
    }

    public int getFuelLimit() {
        return fuelLimit;
    }

    public void setFuelLimit(int fuelLimit) {
        this.fuelLimit = fuelLimit;
    }
}

package Fuel_Station.Fuel_Station.dto.response;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class OwnerResponse {
    private Long ownerId;
    private String name;
    private String nic;
    private String contactNumber;
    private String email;
    private String username;
}

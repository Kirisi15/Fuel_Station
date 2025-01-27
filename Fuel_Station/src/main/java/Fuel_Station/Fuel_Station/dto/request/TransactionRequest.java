package Fuel_Station.Fuel_Station.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data

public class TransactionRequest {
    private Double quantity;

    private LocalDateTime dateTime;
}

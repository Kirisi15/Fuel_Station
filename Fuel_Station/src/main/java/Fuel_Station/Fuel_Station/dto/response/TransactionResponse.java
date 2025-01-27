package Fuel_Station.Fuel_Station.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class TransactionResponse {
    private Long  transactionId;
    private Double quantity;
    private LocalDateTime dateTime;
}

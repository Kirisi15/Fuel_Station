package Fuel_Station.Fuel_Station.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class TimePeriodResponse {
    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long createdUserId;
    private Long lastModifiedUserId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}

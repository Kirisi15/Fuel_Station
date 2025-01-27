package Fuel_Station.Fuel_Station.dto.request;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@Data
public class TimePeriodRequest {
    private LocalDateTime start;
    private LocalDateTime end;
    private Long createdUserId;
    private Long lastModifiedUserId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}

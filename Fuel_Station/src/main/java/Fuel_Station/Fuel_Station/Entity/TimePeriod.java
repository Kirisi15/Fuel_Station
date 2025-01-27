package Fuel_Station.Fuel_Station.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.scheduling.annotation.Schedules;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity

@Table(name = "time_period")
public class TimePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @Column(updatable = false)
    @CreatedBy
    private Long createdUserId;

    @LastModifiedBy
    private Long lastModifiedUserId;

    @Column(updatable = false,name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}

package Fuel_Station.Fuel_Station;

import Fuel_Station.Fuel_Station.Entity.TimePeriod;
import Fuel_Station.Fuel_Station.Repository.TimeLimitRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Component
public class ScheduledTask {
    private TimeLimitRepository timeLimitRepository;
    @Scheduled(cron = "0 0 0 * * MON", zone = "Asia/Colombo")
    @Transactional
    public void everyWeek() {
        // Get the current week's start and end dates
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekStart = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).toLocalDate().atStartOfDay();
        LocalDateTime weekEnd = weekStart.plusDays(6).withHour(23).withMinute(59).withSecond(59);

        // Create a new TimePeriod record
        TimePeriod timePeriod = new TimePeriod();
        timePeriod.setStart(weekStart);
        timePeriod.setEnd(weekEnd);

        // Set auditing fields (if required)
        // You can use Spring Security or other methods to set `createdUserId` or `lastModifiedUserId`
        timePeriod.setCreatedUserId(1L); // Example: hardcoded user ID for now
        timePeriod.setCreatedDate(LocalDateTime.now());

        // Save the record in the database
        timeLimitRepository.save(timePeriod);

        System.out.println("Weekly fuel quota updated for period: " + weekStart + " to " + weekEnd);
    }


}

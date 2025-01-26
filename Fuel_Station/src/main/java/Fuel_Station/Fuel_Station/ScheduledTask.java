package Fuel_Station.Fuel_Station;

import Fuel_Station.Fuel_Station.Entity.TimePeriod;
import Fuel_Station.Fuel_Station.Repository.TimePeriodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Component
public class ScheduledTask {
    @Autowired
    private TimePeriodRepository timePeriodRepository;
    @Scheduled(cron = "0 0 0 * * MON", zone = "Asia/Colombo")
    @Transactional
    public void everyWeek() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekStart = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).toLocalDate().atStartOfDay();
        LocalDateTime weekEnd = weekStart.plusDays(6).withHour(23).withMinute(59).withSecond(59);


        TimePeriod timePeriod = new TimePeriod();
        timePeriod.setStart(weekStart);
        timePeriod.setEnd(weekEnd);

        timePeriod.setCreatedUserId(1L);
        timePeriod.setCreatedDate(LocalDateTime.now());

        timePeriodRepository.save(timePeriod);

        System.out.println("Weekly fuel quota updated for period: " + weekStart + " to " + weekEnd);
    }


}

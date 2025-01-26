package Fuel_Station.Fuel_Station;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {


    @Scheduled(cron = "0 0 0 * * MON")
    public void updateFuelQuota() {
        System.out.println("Updating fuel quota: " + System.currentTimeMillis() / 1000);
    }
}

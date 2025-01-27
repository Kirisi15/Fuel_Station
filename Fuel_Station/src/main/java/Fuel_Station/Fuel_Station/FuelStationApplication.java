package Fuel_Station.Fuel_Station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FuelStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelStationApplication.class, args);
	}

}

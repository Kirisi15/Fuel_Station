package Fuel_Station.Fuel_Station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.example.fuelmanagement") // Adjust to your package structure
@EnableJpaRepositories(basePackages = "com.example.fuelmanagement.repository")
@EntityScan(basePackages = "com.example.fuelmanagement.entity")

@SpringBootApplication
public class FuelStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelStationApplication.class, args);
	}

}

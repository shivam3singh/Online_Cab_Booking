package com.urbancab;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Urban Cab | Where the City Rides", description = "Online Cab Booking System", version = "1.0"))
public class UrbanCabApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanCabApplication.class, args);
	}

}

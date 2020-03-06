package com.gryszq.microservices.reservationmodelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ReservationModelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationModelServiceApplication.class, args);
	}

}

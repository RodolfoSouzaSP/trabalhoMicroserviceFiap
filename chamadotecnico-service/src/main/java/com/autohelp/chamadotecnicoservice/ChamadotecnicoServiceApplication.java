package com.autohelp.chamadotecnicoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ChamadotecnicoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChamadotecnicoServiceApplication.class, args);
	}

}

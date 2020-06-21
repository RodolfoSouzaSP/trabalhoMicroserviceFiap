package com.autohelp.filmeassistidoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class FilmeassistidoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmeassistidoServiceApplication.class, args);
	}

}

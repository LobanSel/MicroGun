package com.mirea.arsenalservice;

import com.mirea.arsenalservice.model.Arsenal;
import com.mirea.arsenalservice.repo.ArsenalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ArsenalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArsenalServiceApplication.class, args);
	}
}



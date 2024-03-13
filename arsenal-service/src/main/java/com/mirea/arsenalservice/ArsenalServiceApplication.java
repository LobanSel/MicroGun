package com.mirea.arsenalservice;

import com.mirea.arsenalservice.model.Arsenal;
import com.mirea.arsenalservice.repo.ArsenalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArsenalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArsenalServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadArsenal(ArsenalRepository arsenalRepository) {
		return args -> {
			Arsenal arsenal = new Arsenal();
			arsenal.setGunCode("M4A1-S");
			arsenal.setQuantity(1250);

			Arsenal arsenal1 = new Arsenal();
			arsenal1.setGunCode("AK-47");
			arsenal1.setQuantity(500);

			arsenalRepository.save(arsenal);
			arsenalRepository.save(arsenal1);

		};

	}

}

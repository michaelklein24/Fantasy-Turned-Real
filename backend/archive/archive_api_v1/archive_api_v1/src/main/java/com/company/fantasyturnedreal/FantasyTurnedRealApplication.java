package com.company.fantasyturnedreal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FantasyTurnedRealApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyTurnedRealApplication.class, args);
	}

}

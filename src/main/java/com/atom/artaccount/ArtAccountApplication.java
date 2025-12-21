package com.atom.artaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArtAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtAccountApplication.class, args);
	}

}

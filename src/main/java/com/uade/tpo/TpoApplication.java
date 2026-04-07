package com.uade.tpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.uade.tpo.repository")
public class TpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpoApplication.class, args);
	}

}

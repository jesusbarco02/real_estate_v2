package com.salesianostriana.dam.RealStateV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RealStateV2Application {

	public static void main(String[] args) {
		SpringApplication.run(RealStateV2Application.class, args);
	}

}

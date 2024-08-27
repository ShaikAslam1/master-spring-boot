package com.aslam.masterspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MasterSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterSpringBootApplication.class);
	}

}

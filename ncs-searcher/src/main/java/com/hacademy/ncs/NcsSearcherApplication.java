package com.hacademy.ncs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NcsSearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(NcsSearcherApplication.class, args);
	}

}

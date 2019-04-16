package com.energyaustralia.codetest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodetestApplication {

	private static final Logger log = LoggerFactory.getLogger(CodetestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CodetestApplication.class, args);
		log.info("Application Started");
	}
}

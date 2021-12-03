package com.openclassrooms.safetynets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {
	private static final Logger logger = LogManager.getLogger(AlertsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
		logger.debug("This is a debug statments");
		logger.info("This is info");
	}

}

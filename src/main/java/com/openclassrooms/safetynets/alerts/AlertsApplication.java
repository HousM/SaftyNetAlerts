package com.openclassrooms.safetynets.alerts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {
	private static final Logger logger = LogManager.getLogger(AlertsApplication.class);

	public static void main(String[] args) {

		// PropertiesConfigurator is used to configure logger from properties file
		// PropertyConfigurator.configure(AlertsApplication.class.getResource("/log4j.properties"));
		SpringApplication.run(AlertsApplication.class, args);
		logger.debug("This is a debug statments");
		logger.info("This is info");
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");

	}

}

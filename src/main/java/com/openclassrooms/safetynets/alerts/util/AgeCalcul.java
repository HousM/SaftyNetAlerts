package com.openclassrooms.safetynets.alerts.util;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AgeCalcul {

	private Logger logger = LoggerFactory.getLogger(AgeCalcul.class);

	public int getAge(LocalDate birthdate) {

		LocalDate currentDate = LocalDate.now();
		int age = Period.between(birthdate, currentDate).getYears();

		if (birthdate.isAfter(currentDate)) {
			logger.error("Birthdate is incorrect:" + birthdate);
			throw new IllegalArgumentException("Invalid: birthdate has not yet been achieved");
		} else if (age < 1) {
			logger.debug("The Baby's age is less then one year");
			age++;
		}

		return age;
	}
}

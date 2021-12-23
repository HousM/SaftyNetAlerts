package com.openclassrooms.safetynets.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AgeTest {
	private static AgeCalcul ageCalculator = new AgeCalcul();

	@Test
	@Tag("ValidAge")
	@DisplayName("If birthDate corresponds to a ten years old person, age should be ten")
	public void givenATenYearsOldBirthDate_whenGetAge_thenReturnCorrectAge() {
		LocalDate birthDate = LocalDate.now().minusYears(10);

		int age = ageCalculator.getAge(birthDate);

		assertThat(age).isEqualTo(10);
	}

	@Test
	@Tag("Exception")
	@DisplayName("If birthDate is in future, getAge should raise an IllegalArgumentException")
	public void givenAFutureBirthDate_whenGetAge_thenIllegalArgumentExceptionIsThrown() {
		int age = 5;
		LocalDate birthDate = LocalDate.now().plusYears(age);

		assertThatIllegalArgumentException().isThrownBy(() -> ageCalculator.getAge(birthDate));
	}
}

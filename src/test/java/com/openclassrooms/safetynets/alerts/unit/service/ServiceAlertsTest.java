package com.openclassrooms.safetynets.alerts.unit.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.ReadJsonData;
import com.openclassrooms.safetynets.alerts.service.AlertsService;
import com.openclassrooms.safetynets.alerts.service.FireStationService;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;
import com.openclassrooms.safetynets.alerts.service.PersonService;
import com.openclassrooms.safetynets.alerts.util.AgeCalcul;

@ExtendWith(MockitoExtension.class)
public class ServiceAlertsTest {

	@InjectMocks
	private AlertsService alertsService;
	@Mock
	private FireStationService fireStationService;
	@Mock
	private MedicalRecordService medicalRecordService;
	@Mock
	private PersonService personService;

	private ReadJsonData dataStore;

	@Mock
	private AgeCalcul ageCalculator;

	private FireDTO fire;

	private static List<Person> personList;

	private static Person person1;

	private static Person person2;

	private static Person person3;

	private static MedicalRecord med1;

	private static MedicalRecord med2;
	@Mock
	private static MedicalRecord med3;

	@Before
	public void setUp() {
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person2 = new Person("Tony", "Cooper", "1509 Culver St", "Culver",
				97451, "841-874-6874", "tcoop@ymail.com");
		person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
				97451, "841-874-7458", "gramps@email.com");
		personList = Arrays.asList(person1, person2, person3);

		med1 = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		med2 = new MedicalRecord("Tony", "Cooper", "09/18/2015",
				Arrays.asList("noxidian:100mg"), Arrays.asList(""));
		med3 = new MedicalRecord("Eric", "Cadigan", "02/07/1991",
				Arrays.asList(""), Arrays.asList("peanut"));

		fire = new FireDTO(2, personList);
	}

	@Test
	public void personsByStationTest() throws Exception {
		when(dataStore.getPersonList()).thenReturn(personList);
		when(fireStationService.getAddressesByStation(2)).thenReturn(Arrays.asList("1509 Culver St"));
		when(medicalRecordService.getMedicalRecordById("John", "Boyd")).thenReturn(med1);
		when(medicalRecordService.getMedicalRecordById("Tony", "Cooper")).thenReturn(med2);
		when(ageCalculator.getAge(LocalDate.of(1984, 3, 6))).thenReturn(37);
		when(ageCalculator.getAge(LocalDate.of(2015, 9, 18))).thenReturn(6);

		PersonDTO result = alertsService.getPersonsByStation(2);

		assertNull(result);

	}

}

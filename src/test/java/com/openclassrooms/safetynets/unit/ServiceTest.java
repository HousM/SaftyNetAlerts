package com.openclassrooms.safetynets.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.service.AlertsService;
import com.openclassrooms.safetynets.alerts.service.FireStationService;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;
import com.openclassrooms.safetynets.alerts.service.PersonService;
import com.openclassrooms.safetynets.alerts.util.AgeCalcul;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

	@InjectMocks
	private AlertsService alertsService;

	@Mock
	private FireStationService fireStationService;

	@Mock
	private MedicalRecordService medicalRecordService;

	@Mock
	private PersonService personService;

	@Mock
	private AgeCalcul ageCalcul;

	private static List<Person> personList;
	private static Person person1;
	private static Person person2;
	private static Person person3;

	private static MedicalRecord med1;
	private static MedicalRecord med2;
	private static MedicalRecord med3;

	@BeforeEach
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
	}

	@Test
	@Tag("PersonInfo")
	@DisplayName("Given a person Id, when getInfoByIdentity, then result should match expected person info")
	public void givenAPersonId_whenGetPersonByIdentity_thenReturnExpectedPersonInfo() throws Exception {
		when(personService.getPersonList()).thenReturn(personList);
		when(medicalRecordService.getMedicalRecordById(toString(), toString())).thenReturn(med1);
		when(ageCalcul.getAge(any(LocalDate.class))).thenReturn(36);

		PersonDTO result = alertsService.getPersonByIdentity("John", "Boyd");

		assertThat(result).isNotNull();
		assertThat(result.getPersonInfo().get(0).getAge()).isEqualTo(36);
		InOrder inOrder = inOrder(personService, medicalRecordService, ageCalcul);
		inOrder.verify(personService).getPersonList();
		inOrder.verify(medicalRecordService).getMedicalRecordById(toString(), toString());
		inOrder.verify(ageCalcul).getAge(any(LocalDate.class));
	}

	@Test
	@Tag("Fire")
	@DisplayName("Given an address, when getPersonsByAddress, then result should match expected persons by address " +
			"and station number")
	public void givenAnAddress_whenGetPersonsByAddress_thenReturnExpectedPersonsByAddressAndStationNumber()
			throws Exception {
		when(personService.getPersonsByAddress("1509 Culver St")).thenReturn(Arrays.asList(person1,
				person2));
		when(medicalRecordService.getMedicalRecordById("John", "Boyd")).thenReturn(med1);
		when(medicalRecordService.getMedicalRecordById("Tony", "Cooper")).thenReturn(med2);
		when(ageCalcul.getAge(LocalDate.of(1984, 3, 6))).thenReturn(36);
		when(ageCalcul.getAge(LocalDate.of(2015, 9, 18))).thenReturn(5);
		when(fireStationService.getFireStationByAddress("1509 Culver St")).thenReturn(new FireStation(
				"1509 Culver St", 1));

		FireDTO result = alertsService.getPersonsByAddress("1509 Culver St");

		assertThat(result.getStation()).isEqualTo(1);
		assertThat(result.getPersons().size()).isEqualTo(2);
		verify(personService).getPersonsByAddress(toString());
		verify(medicalRecordService, times(2)).getMedicalRecordById(toString(), toString());
		verify(ageCalcul, times(2)).getAge(any(LocalDate.class));
		verify(fireStationService).getFireStationByAddress(toString());
	}

	@Test
	@Tag("Flood")
	@DisplayName("Given a stations list, when getHouseholdsByStation, then result should match expected households " +
			"by station")
	public void givenAStationList_whenGetHouseholdsByStation_thenReturnExpectedHouseholdsByStation() throws Exception {
		when(fireStationService.getAddressesByStation(3)).thenReturn(Arrays.asList("1509 Culver St"));
		when(fireStationService.getAddressesByStation(1)).thenReturn(Arrays.asList("951 LoneTree Rd"));
		when(personService.getPersonsByAddress("1509 Culver St")).thenReturn(Arrays.asList(person1, person2));
		when(personService.getPersonsByAddress("951 LoneTree Rd")).thenReturn(Arrays.asList(person3));
		when(medicalRecordService.getMedicalRecordById("John", "Boyd")).thenReturn(med1);
		when(medicalRecordService.getMedicalRecordById("Tony", "Cooper")).thenReturn(med2);
		when(medicalRecordService.getMedicalRecordById("Eric", "Cadigan")).thenReturn(med3);
		when(ageCalcul.getAge(any(LocalDate.class))).thenReturn(anyInt());

		FloodDTO result = alertsService.getHouseholdsByStation(Arrays.asList(3, 1));

		assertThat(result.gethouseholdsByStations()).size().isEqualTo(2);
		assertThat(result.gethouseholdsByStations().get(0).getStation()).isEqualTo(3);
		verify(fireStationService, times(2)).getAddressesByStation(anyInt());
		verify(personService, times(2)).getPersonsByAddress(toString());
		verify(medicalRecordService, times(3)).getMedicalRecordById(toString(), toString());
		verify(ageCalcul, times(3)).getAge(any(LocalDate.class));
	}

}

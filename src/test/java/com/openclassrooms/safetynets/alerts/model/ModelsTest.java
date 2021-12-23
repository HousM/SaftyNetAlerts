package com.openclassrooms.safetynets.alerts.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.dto.PhoneDTO;
import com.openclassrooms.safetynets.alerts.repository.ReadJsonData;

@SuppressWarnings("unused")
public class ModelsTest {

	@Mock
	private ReadJsonData dataStore;

	@Mock
	private Person person;
	@Mock
	private FireStation fire1;
	@Mock
	private Person person2;
	@Mock
	private Person person3;
	@Mock
	private Person person4;
	@Mock
	private Person person1;

	@Mock
	private PersonDTO persondto;

	@Mock
	private PersonDTO persondto1;

	@Mock
	private PersonDTO persondto2;

	@Mock
	private PersonDTO persondto3;

	@Mock
	private FireStation fire2;
	@Mock
	private List<Person> personAddress = Arrays.asList(person1, person2);
	@Mock
	List<FireStation> households = Arrays.asList(fire1, fire2);
	@Mock
	private FireStation fire3;
	@Mock
	private MedicalRecord med1;

	@Mock
	private MedicalRecord med;
	@Mock
	private MedicalRecordDTO meddto;
	@Mock
	private MedicalRecordDTO meddto1;

	@Mock
	private FireDTO firedto1;

	@Mock
	private FireDTO firedto2;

	@Mock
	private PhoneDTO phone;

	@Mock
	private FloodDTO flood;

	@Mock
	private FloodDTO flood1;

	@Test
	public void ModelsTest() {

//		Model Person
		person = new Person("Mark", "Boyd", "1509 Culver St", "Culver",
				97451, "898-353-6978", "maboyd@email.com");
		person1 = new Person("Mark", "Boyd");
		person2 = new Person("Boyd", "841-874-6512", 50, Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		person3 = new Person("Boyd", "841-874-6512", 50, "maboyd@email.com", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		person4 = new Person("Mark", "Boyd", "1509 Culver St", "841-874-6512");

//      Model FireStation
		fire1 = new FireStation("29 15th St", 2);
		fire2 = new FireStation("29 15th St", personAddress);
		fire3 = new FireStation(2, households);

		persondto = new PersonDTO(personAddress, 1, 2);
		persondto1 = new PersonDTO("29 15th St", personAddress);
		persondto2 = new PersonDTO(1, households);
		persondto3 = new PersonDTO(personAddress);
		firedto1 = new FireDTO(1, Arrays.asList(person, person2));
		firedto2 = new FireDTO("29 15th St", 2);
		phone = new PhoneDTO(Arrays.asList("00000000", "06060606"));
		flood = new FloodDTO(households);
		flood1 = new FloodDTO(1, personAddress);
		meddto = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		meddto1 = new MedicalRecordDTO();

//		Model MedicalRecord
		med1 = new MedicalRecord("JohnN", "BoydN", "03/06/1985",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		med = new MedicalRecord();

		assertEquals(person1.getFirstName(), "Mark");
		assertEquals(person.getLastName(), "Boyd");
		assertEquals(person.getAddress(), "1509 Culver St");
		assertEquals(person.getCity(), "Culver");
		assertEquals(person.getZip(), 97451);
		assertEquals(person.getEmail(), "maboyd@email.com");
		assertEquals(person.getPhone(), "898-353-6978");
		assertEquals(person2.getMedicationsList(), Arrays.asList("aznol:350mg"));
		assertEquals(person2.getAllergiesList(), Arrays.asList("nillacilan"));
		assertEquals(person3.getAge(), 50);
		assertEquals(person4.getPhone(), "841-874-6512");

		assertNotNull(fire2.getPersonToSave());
		assertNotNull(fire2.getPersonAddress());
		assertNotNull(fire3.getHouseholds());

		assertNotNull(persondto.getPerson());
		assertNotNull(persondto.getAdultNumber());
		assertNotNull(persondto.getChildNumber());
		assertNotNull(persondto1.getStation());
		assertNotNull(persondto1.getAddress());
		assertNotNull(persondto1.getPersonAddress());

		assertNotNull(persondto2.getHouseholds());
		assertNotNull(persondto2.getStation());

//		assertNotNull(persondto3.getPersonInfo());
//		assertNotNull(persondto3.getPersonsByStation());

		assertNotNull(firedto1.getStation());
		assertNotNull(firedto1.getPersons());
		assertNotNull(firedto2.getAddress());
		assertNotNull(firedto2.getStation());

		assertNotNull(phone.getPhones());
		assertNotNull(flood1.getHouseholds());
		assertNotNull(flood1.getStation());
		assertNotNull(flood.gethouseholdsByStations());
		assertNotNull(meddto1.toMedicalRecordDTO(med1));
		med.addMedications("aznol:350mg");
		med.addAllergies("nillacilan");

		assertEquals(med1.getBirthdate(), "03/06/1985");
		assertEquals(med1.getMedicationsList(), Arrays.asList("aznol:350mg"));
		assertEquals(med1.getAllergiesList(), Arrays.asList("nillacilan"));

		assertEquals(meddto.getFirstName(), "JohnN");
		assertEquals(meddto.getLastName(), "BoydN");
		assertEquals(meddto.getBirthDate(), "03/06/1985");
		assertEquals(meddto.getMedications(), Arrays.asList("aznol:350mg"));
		assertEquals(meddto.getAllergies(), Arrays.asList("nillacilan"));

	}

}

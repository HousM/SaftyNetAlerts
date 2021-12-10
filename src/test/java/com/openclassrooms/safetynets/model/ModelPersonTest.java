package com.openclassrooms.safetynets.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.ReadJsonData;

public class ModelPersonTest {

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
	private FireStation fire2;
	@Mock
	private List<Person> personAddress = Arrays.asList(person1, person2);
	@Mock
	List<FireStation> households = Arrays.asList(fire1, fire2);
	@Mock
	private FireStation fire3;
	@Mock
	private MedicalRecord med1;

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

//		Model MedicalRecord
		med1 = new MedicalRecord("JohnN", "BoydN", "03/06/1985",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

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

		assertEquals(med1.getBirthdate(), "03/06/1985");
		assertEquals(med1.getMedicationsList(), Arrays.asList("aznol:350mg"));
		assertEquals(med1.getAllergiesList(), Arrays.asList("nillacilan"));

	}

}

package com.openclassrooms.safetynets.alerts.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.PersonRepository;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class ServicePersonTest {

	@InjectMocks
	private PersonService personService;
	@Mock
	private PersonRepository personRepositoryMock;

	private List<Person> personList;
	@Mock
	private Person person;

	@Test
	@DisplayName("Given a registered person, when updatePerson, then person should be updated correctly")
	public void updatePersonTest()
			throws Exception {
		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personRepositoryMock.update(any(Person.class))).thenReturn(person);
		// Act
		Person result = personService.updatePerson(person);
		// Assert
		assertNotNull(result);
		assertEquals(person, result);

	}

	@Test
	@Tag("deletePerson")
	@DisplayName("Given person Id, when deletePerson, then delete process should be done in correct order")
	public void deletePersonTest()
			throws Exception {

		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(person);
		personService.deletePerson(person.getFirstName(), person.getLastName());

	}

	@Test
	@Tag("getList")
	@DisplayName("verify that, when getList, list is no null")
	public void getListTest() throws Exception {

		List<Person> personList = new ArrayList<Person>();
		when(personRepositoryMock.getPersonList()).thenReturn(personList);
		List<Person> result = personService.getPersonList();
//		assertEquals(personList, result);
		assertThat(result).isNotNull();

	}

	@Test
	@DisplayName("Given a persons by city list, when getPersonsByCity, then the persons by city list should be " +
			"returned correctly")
	public void getPersonsByCityTest()
			throws Exception {
		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		personList = Arrays.asList(person);

		when(personRepositoryMock.findByCity(anyString())).thenReturn(personList);

		List<Person> personsByCity = personService.getPersonsByCity("Culver");
		assertEquals(personList, personsByCity);
		assertThat(personsByCity).isNotNull();

	}

	@Test
	@DisplayName("Given a persons by address list, when getPersonsByAddress, then the persons by address list should" +
			" be returned correctly")
	public void getPersonsByAddressTest()
			throws Exception {

		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		personList = Arrays.asList(person);
		when(personRepositoryMock.findByAddress(anyString())).thenReturn(personList);

		List<Person> personsByAddress = personService.getPersonsByAddress("1509 Culver St");
		assertEquals(person, personsByAddress);
		assertThat(personsByAddress).isNotNull();
	}

	@Test
	@Tag("getPersonsById")
	@DisplayName("Given a Person id, when getPersonsById, then expected person should be returned correctly")
	public void getPersonsByIdTest() throws Exception {
		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(person);

		Person personById = personService.getPersonById(person.getFirstName(), person.getLastName());

		assertThat(personById).isEqualTo(person);

	}

}

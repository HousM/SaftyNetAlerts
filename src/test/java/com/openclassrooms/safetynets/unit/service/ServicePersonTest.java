package com.openclassrooms.safetynets.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.PersonRepository;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class ServicePersonTest {

	@Mock
	private PersonRepository personRepositoryMock;

	@InjectMocks
	private PersonService personService;

	private List<Person> personList;

	@Mock
	private Person person1;
	@Mock
	private Person person2;
	@Mock
	private Person person3;

	@Test
	@DisplayName("Given a registered person, when updatePerson, then person should be updated correctly")
	public void updatePersonTest() throws Exception {
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personRepositoryMock.findByIdentity("John", "Boyd")).thenReturn(person1);
		// Act
		Person result = personService.updatePerson(person1);
		// Assert
		assertNotNull(result);
		assertEquals(person1, result);

	}

	@Test
	@Tag("deletePerson")
	@DisplayName("Given person Id, when deletePerson, then delete process should be done in correct order")
	public void deletePersonTest() throws Exception {

		person2 = new Person("NewFN", "NewLN", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		personService.deletePerson(person2.getFirstName(), person2.getLastName());
		assertThat((personRepositoryMock).findByIdentity("NewFN", "NewLN")).isEqualTo(null);
		;

	}

	@Test
	@Tag("getList")
	@DisplayName("verify that, when getList, list is no null")
	public void getListTest() throws Exception {
		List<Person> result = personService.getPersonList();
		assertThat(result).isNotNull();

	}

	@Test
	@DisplayName("Given a persons by city list, when getPersonsByCity, then the persons by city list should be " +
			"returned correctly")
	public void getPersonsByCityTest()
			throws Exception {
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
				97451, "841-874-7458", "gramps@email.com");
		personList = Arrays.asList(person1, person3);
		when(personRepositoryMock.findByCity("Culver")).thenReturn(personList);

		List<Person> personsByCity = personService.getPersonsByCity("Culver");

		assertThat(personsByCity).isEqualTo(personList);
	}

	@Test
	@DisplayName("Given a persons by address list, when getPersonsByAddress, then the persons by address list should" +
			" be returned correctly")
	public void getPersonsByAddressTest()
			throws Exception {
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
				97451, "841-874-7458", "gramps@email.com");
		List<Person> personsByAddressExpected = Arrays.asList(person1, person3);
		when(personRepositoryMock.findByAddress(anyString())).thenReturn(personsByAddressExpected);

		List<Person> personsByAddress = personService.getPersonsByAddress(anyString());

		assertThat(personsByAddress).isEqualTo(personsByAddressExpected);
		verify(personRepositoryMock).findByAddress(anyString());
	}

	@Test
	@Tag("getPersonsById")
	@DisplayName("Given a Person id, when getPersonsById, then expected person should be returned correctly")
	public void getPersonsByIdTest() throws Exception {
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(person1);

		Person personById = personService.getPersonById(person1.getFirstName(), person1.getLastName());

		assertThat(personById).isEqualTo(person1);

	}

}

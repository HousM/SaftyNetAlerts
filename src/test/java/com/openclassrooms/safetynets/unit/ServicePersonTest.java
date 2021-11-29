package com.openclassrooms.safetynets.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
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

	private PersonRepository personRepository;
	private static List<Person> personList;
	private static Person person1;
	private static Person person2;
	private static Person person3;
	private static Person person;

	@Before
	public void setUp() {
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person2 = new Person("Tony", "Cooper", "1509 Culver St", "Culver",
				97451, "841-874-6874", "tcoop@ymail.com");
		person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
				97451, "841-874-7458", "gramps@email.com");
		personList = Arrays.asList(person1, person2, person3);

	}

	@Test
	@Tag("CreatePerson")
	@DisplayName("If person is not registered, when createPerson, then person should be saved correctly")
	public void givenAnUnRegisteredPerson_whenCreatePerson_thenPersonShouldBeSavedCorrectly() throws Exception {
		when(personRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(null);
		// when(personRepository.findByIdentity("John", "Boyd")).thenReturn(person1);
		when(personRepositoryMock.save(person1)).thenReturn(person1);

		Person personSaved = personService.createPerson(person);

		assertNotNull(personSaved);
		assertThat(personSaved).isEqualTo(person1);

		verify(personRepositoryMock).findByIdentity(anyString(), anyString());
		verify(personRepository.findByIdentity(personSaved.getFirstName(), personSaved.getLastName()));
		verify(personRepositoryMock).save(any(Person.class));
	}

	@Test
	@Tag("UpdatePerson")
	@DisplayName("Given a registered person, when updatePerson, then person should be updated correctly")
	public void givenAPerson_whenUpdatePerson_thenPersonShouldBeUpdateCorrectly() throws Exception {
		person = new Person("John", "Boyd", "892 Downing Ct", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(person);
		when(personRepository.findByIdentity("John", "Boyd")).thenReturn(person);

		Person personUpdated = personService.updatePerson(person);

		assertThat(personUpdated).isEqualTo(person);
		verify(personRepositoryMock).findByIdentity(anyString(), anyString());

	}

	@Test
	@Tag("DeletePerson")
	@DisplayName("Given person Id, when deletePerson, then delete process should be done in correct order")
	public void givenAPersonId_whenDeletePerson_thenDeletingShouldBeDoneInCorrectOrder() throws Exception {
		when(personRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(person1);

		personService.deletePerson(person1.getFirstName(), person1.getLastName());

		verify(personRepositoryMock).findByIdentity(anyString(), anyString());
		verify(personRepositoryMock).delete(any(Person.class));
	}

	@Test
	@Tag("GetList")
	@DisplayName("Given a person list, when getList, then result should match expected person list")
	public void givenAPersonList_whenGetList_thenReturnExpectedPersonList() throws Exception {
		when(personRepositoryMock.getPersonList()).thenReturn(personList);

		List<Person> result = personService.getPersonList();

		assertThat(result).isEqualTo(personList);
		verify(personRepositoryMock).getPersonList();
	}

	@Test
	@Tag("GetPersonsByCity")
	@DisplayName("Given a persons by city list, when getPersonsByCity, then the persons by city list should be " +
			"returned correctly")
	public void givenPersonsByCityList_whenGetPersonsByCity_thenPersonsByCityListShouldBeReturnCorrectly()
			throws Exception {
		when(personRepositoryMock.findByCity(anyString())).thenReturn(personList);

		List<Person> personsByCity = personService.getPersonsByCity(anyString());

		assertThat(personsByCity).isEqualTo(personList);
		verify(personRepositoryMock).findByCity(anyString());
	}

	@Test
	@Tag("GetPersonsByAddress")
	@DisplayName("Given a persons by address list, when getPersonsByAddress, then the persons by address list should" +
			" be returned correctly")
	public void givenPersonsByAddressList_whenGetPersonsByAddress_thenPersonsByAddressListShouldBeReturnCorrectly()
			throws Exception {
		List<Person> personsByAddressExpected = Arrays.asList(person2, person3);
		when(personRepositoryMock.findByAddress(anyString())).thenReturn(personsByAddressExpected);

		List<Person> personsByAddress = personService.getPersonsByAddress(anyString());

		assertThat(personsByAddress).isEqualTo(personsByAddressExpected);
		verify(personRepositoryMock).findByAddress(anyString());
	}

	@Test
	@Tag("GetPersonsById")
	@DisplayName("Given a Person id, when getPersonsById, then expected person should be returned correctly")
	public void givenAPersonId_whenGetPersonsById_thenExpectedPersonShouldBeReturnCorrectly() throws Exception {
		when(personRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(person1);

		Person personById = personService.getPersonById(person1.getFirstName(), person1.getLastName());

		assertThat(personById).isEqualTo(person1);

		verify(personRepositoryMock).findByIdentity(anyString(), anyString());

	}

}

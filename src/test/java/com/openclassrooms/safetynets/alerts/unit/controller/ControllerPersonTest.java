package com.openclassrooms.safetynets.alerts.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.controller.PersonController;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.PersonRepository;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class ControllerPersonTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PersonRepository personRepository;

	@MockBean
	private PersonService personService;

	private Person person;
	@Mock
	private Person person1;

	@Before
	void setup() {
		new ObjectMapper();

		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");

	}

	@Test
	@Tag("POST-Person")
	public void testCreatePerson() throws Exception {
		// Arrange

		String personRecord = "{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";

		when(personRepository.save(any(Person.class))).thenReturn(person);

		mockMvc.perform(MockMvcRequestBuilders.post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(personRecord))
				.andExpect(status().is(201));

	}

	@Test
	@Tag("PUT-Person")
	void testPutPerson() throws Exception {

		String personRecord = "{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}";
		when(personService.updatePerson(person))
				.thenReturn(person);

		mockMvc.perform(MockMvcRequestBuilders.put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(personRecord))
				.andExpect(status().isOk());
	}

	@Test
	@Tag("DELETE-Person")
	void testDeletePerson() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.delete("/person")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
				.andExpect(status().isOk());

		verify(personService).deletePerson(anyString(), anyString());
	}

	@Test
	@Tag("GET-Person")
	public void TestGetPerson() throws Exception {
		when(personService.getPersonById(anyString(), anyString())).thenReturn(person);

		mockMvc.perform(MockMvcRequestBuilders.get("/person/")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
				.andExpect(status().isOk());

	}

}

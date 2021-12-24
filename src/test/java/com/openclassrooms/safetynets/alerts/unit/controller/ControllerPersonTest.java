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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.controller.PersonController;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class ControllerPersonTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	private ObjectMapper objectMapper;
	private Person person;
	private PersonController personController;
	@Mock
	private Person person1;

	@Before
	void setup() {
		objectMapper = new ObjectMapper();
		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");

	}

	@Test
	@Tag("POST-Person")
	public void testCreatePerson() throws Exception {

		// Arrange

		when(personService.createPerson(any(Person.class))).thenReturn(person);

//		// Act
//		mockMvc
//				.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(person)))
//				.andExpect(status().isCreated()).andReturn();
//
//		// Assert
//		verify(personService).createPerson(any(Person.class));

	}

	@Test
	@Tag("PUT-Person")
	void testPutPerson() throws Exception {

		when(personService.updatePerson(person))
				.thenReturn(person);

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
	public void givenValidPersonIdParam_whenGetRequest_thenReturnOkStatus() throws Exception {
		when(personService.getPersonById(anyString(), anyString())).thenReturn(person);

		mockMvc.perform(MockMvcRequestBuilders.get("/person")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
				.andExpect(status().isOk());

		verify(personService).getPersonById(anyString(), anyString());
	}

}

package com.openclassrooms.safetynets.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
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

	@Mock
	private Person person1;

	@Before
	void setup() {
		objectMapper = new ObjectMapper();
		person = new Person("John", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person1 = new Person("Mark", "Boyd", "1509 Culver St", "Culver",
				97451, "898-353-6978", "maboyd@email.com");
	}

	@Test
	@Tag("POST-Person")
	public void givenAPersonToAddWithMissingId_whenPostRequest_thenReturnBadRequestStatus() throws Exception {
		String jsonContent = objectMapper.writeValueAsString(person1);
		mockMvc.perform(MockMvcRequestBuilders.post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
				.andExpect(status().isBadRequest());

		verify(personService).createPerson(any(Person.class));
	}

	@Test
	@Tag("PUT-Person")
	void testPutPerson() throws Exception {
		// Arrange
		Person personToUpdate = new Person("John2", "Boyd2", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd2@email.com");
		String jsonContent = objectMapper.writeValueAsString(personToUpdate);
		Person personUpdated = new Person("John2", "Boyd2", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd2@email.com");
		when(personService.updatePerson(any(Person.class))).thenReturn(personUpdated);

		// Act
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/person").contentType(MediaType.APPLICATION_JSON)
						.content(jsonContent))
				.andExpect(status().isOk()).andReturn();

		// Assert
		verify(personService).updatePerson(any(Person.class));
		Person personResult = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<Person>() {
				});
		assertNotNull(personResult);
		assertEquals(personToUpdate.getEmail(), personResult.getEmail());
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

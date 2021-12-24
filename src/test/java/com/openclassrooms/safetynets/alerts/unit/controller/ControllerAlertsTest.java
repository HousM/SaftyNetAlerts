package com.openclassrooms.safetynets.alerts.unit.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.openclassrooms.safetynets.alerts.controller.AlertsController;
import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.dto.PhoneDTO;
import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.service.AlertsService;
import com.openclassrooms.safetynets.alerts.service.FireStationService;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@WebMvcTest(controllers = AlertsController.class)
public class ControllerAlertsTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlertsService alertsService;
	@MockBean
	private PersonService personService;
	@MockBean
	private FireStationService fireStationService;

	private ObjectMapper objectMapper;

	private FireStation fire1;
	private FireStation fire2;

	private Person person;
	private PersonDTO persondto;

	@Mock
	private ResponseEntity<PhoneDTO> rep;

	private Person person1;
	private Person person2;
	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {

		person1 = new Person("JohnN", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person2 = new Person("JohnN", "Boyd", "15 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");

	}

	@Test
	public void getPersonsByStationTest() throws Exception {

		PersonDTO person = new PersonDTO(1, Arrays.asList(fire1, fire2));
		when(alertsService.getPersonsByStation(1)).thenReturn(person);
//		mockMvc.perform(MockMvcRequestBuilders.get("/stationNumbe"))
//				.andExpect(status().isOk());

	}

	@Test
	public void getPersonsByAddressTest() throws Exception {

		FireDTO fire = new FireDTO("Adresse", 1);
		when(alertsService.getPersonsByAddress("Adresse")).thenReturn(fire);

	}

	@Test
	public void getHouseholdsByStationTest() throws Exception {

		FloodDTO flood = new FloodDTO(1, Arrays.asList(person1, person2));
		when(alertsService.getHouseholdsByStation(Arrays.asList(1))).thenReturn(flood);

	}

	@Test
	void testGetPhoneNumbersForStationNumber() throws Exception {
//		JsonNode phone = new PhoneDTO(Arrays.asList("00000000", "06060606"));
		ArrayNode expected = mapper.createArrayNode();
		expected.addObject().put("phone", "1-1111");

//		rep = new ResponseEntity<>(expected, HttpStatus.OK);
		List<Person> persons = Arrays.asList(person1, person2);
		List<String> addresses = Arrays.asList("15 Culver St", "1509 Culver St");
		when(alertsService.getPhonesByStation(anyInt())).thenReturn(expected);
		when(personService.getPersonList()).thenReturn(persons);
		when(fireStationService.getAddressesByStation(anyInt())).thenReturn(addresses);

		mockMvc
				.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=1")
						.accept(MediaType.APPLICATION_JSON));

		assertNotNull((alertsService).getPhonesByStation(107));
	}

	@Test
	@Tag("GET-Person")
	public void getPersonInfoByIdentityTest() throws Exception {
		PersonDTO persondto = new PersonDTO(Arrays.asList(new Person("Boyd", "1509 Culver St",
				22, "jaboyd@email.com", Arrays.asList(""), Arrays.asList("peanut"))));
		when(alertsService.getPersonByIdentity(anyString(), anyString())).thenReturn(persondto);

		assertNotNull((alertsService).getPersonByIdentity("John", "Boyd"));

	}

}

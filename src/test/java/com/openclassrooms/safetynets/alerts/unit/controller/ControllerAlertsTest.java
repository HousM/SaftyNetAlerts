package com.openclassrooms.safetynets.alerts.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
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
import com.openclassrooms.safetynets.alerts.controller.AlertsController;
import com.openclassrooms.safetynets.alerts.dto.ChildDTO;
import com.openclassrooms.safetynets.alerts.dto.CommunityEmailDTO;
import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonInfoDTO;
import com.openclassrooms.safetynets.alerts.dto.PhoneDTO;
import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.model.PersonInfo;
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

	@Mock
	private ResponseEntity<PhoneDTO> rep;

	private Person person1;
	private Person person2;
	ObjectMapper mapper = new ObjectMapper();

	private PersonDTO perdto1;

	private Person per1;

	private Person per2;

	@Before
	public void setUp() {

		person1 = new Person("JohnN", "Boyd", "1509 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		person2 = new Person("JohnN", "Boyd", "15 Culver St", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		per1 = new Person("JohnN", "Boyd", "1509 Culver St", "841-874-6512");
		per2 = new Person("JohnN", "Boyd", "15 Culver St", "841-874-6512");
		perdto1 = new PersonDTO(Arrays.asList(per1), 1, 1);
		new PersonDTO(Arrays.asList(per2), 1, 2);
		new FireStation("1509 Culver St", 3);
		new FireStation("29 15th St", 2);

	}

	@Test
	public void getPersonsByStationTest() throws Exception {

		when(fireStationService.getAddressesByStation(anyInt())).thenReturn(Arrays.asList("1509 Culver St"));
		when(alertsService.getPersonsByStation(anyInt())).thenReturn(perdto1);

		mockMvc.perform(MockMvcRequestBuilders.get("/fireStation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("stationNumber", "3"))
				.andExpect(status().isOk());

	}

	@Test
	public void getPersonsByAddressTest() throws Exception {

		FireDTO fire = new FireDTO("Adresse", 1);
		when(alertsService.getPersonsByAddress(anyString())).thenReturn(fire);
		mockMvc.perform(MockMvcRequestBuilders.get("/fire")
				.contentType(MediaType.APPLICATION_JSON)
				.param("address", "1509 Culver St"))
				.andExpect(status().isOk());

	}

	@Test
	public void getHouseholdsByStationTest() throws Exception {

		FloodDTO flood = new FloodDTO(1, Arrays.asList(person1, person2));
		when(alertsService.getHouseholdsByStation(Arrays.asList(1))).thenReturn(flood);

		mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations")
				.contentType(MediaType.APPLICATION_JSON)
				.param("stations", "1"))
				.andExpect(status().isOk());

	}

	@Test
	public void getPersonInfoByIdentityTest() throws Exception {
		PersonInfoDTO personInfo = new PersonInfoDTO(Arrays.asList(new PersonInfo("Boyd", "1509 Culver St",
				22, "jaboyd@email.com", Arrays.asList(""), Arrays.asList("peanut"))));
		when(alertsService.getPersonByIdentity(anyString(), anyString())).thenReturn(personInfo);
		mockMvc.perform(MockMvcRequestBuilders.get("/personInfo")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "John")
				.param("lastName", "Boyd"))
				.andExpect(status().isOk());

	}

	@Test
	public void getChildByAddressTest() throws Exception {
		when(alertsService.getChildByAddress(anyString())).thenReturn(any(ChildDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.get("/childAlert")
				.contentType(MediaType.APPLICATION_JSON)
				.param("address", "29 15th St"))
				.andExpect(status().isOk());

	}

	@Test
	public void getPhonesByStationTest() throws Exception {
		when(alertsService.getPhonesByStation(anyInt())).thenReturn(any(PhoneDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firestation", "2"))
				.andExpect(status().isOk());

	}

	@Test
	public void getEmailsByCityTest() throws Exception {
		when(alertsService.getEmailsByCity(anyString())).thenReturn(any(CommunityEmailDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail")
				.contentType(MediaType.APPLICATION_JSON)
				.param("city", "Culver"))
				.andExpect(status().isOk());

	}

}

package com.openclassrooms.safetynets.alerts.unit.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.controller.FireStationController;
import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.service.FireStationService;

@WebMvcTest(controllers = FireStationController.class)
public class ControllerFireTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FireStationService fireStationService;

	private FireStation fire;

	@Mock
	private ResponseEntity<FireStation> rep1;
	@Mock
	private ResponseEntity<FireStation> rep2;
	@Mock
	private FireStation fire1;
	@Mock
	private FireStationController fireStationController;

	@Before
	public void setUp() {
		new ObjectMapper();

		fire = new FireStation("1509 Culver St", 3);
	}

	@Test
	@Tag("POST-FireStation")
	public void givenAFireStationToAdd_whenPostRequest_thenReturnCreatedStatus() throws Exception {

		String firestationRecord = "{\"station\":\"30\",\"address\":\"4 Binocle Ave\"}";
		when(fireStationService.createFireStation(fire)).thenReturn(fire);
		mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(firestationRecord))
				.andExpect(status().is(201));

	}

	@Test
	public void createFireStationTest() throws Exception {
		fire1 = new FireStation("29 15th St", 2);
		rep2 = new ResponseEntity<>(fire1, HttpStatus.CREATED);
		when(fireStationService.createFireStation(fire1)).thenReturn(fire1);
		when(fireStationController.createFireStation(fire1)).thenReturn(rep2);
		assertNotNull(rep2);
		assertNotNull(fire1);
		assertNotNull((fireStationController).createFireStation(fire1));
	}

	@Test
	@Tag("PUT-FireStation")
	@DisplayName("Given a FireStation to update, when PUT request, then return Ok status")
	public void givenAFireStationToUpdate_whenPutRequest_thenReturnOkStatus() throws Exception {
		String firestationRecord = "{\"station\":\"30\",\"address\":\"4 Binocle Ave\"}";
		when(fireStationService.updateFireStation(fire)).thenReturn(fire);
		mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(firestationRecord))
				.andExpect(status().isOk());

	}

	@Test
	public void updateFireStationTest() throws Exception {
		fire1 = new FireStation("29 15th St", 2);

		rep1 = new ResponseEntity<>(fire, HttpStatus.OK);
		when(fireStationService.updateFireStation(fire1)).thenReturn(fire1);
		when(fireStationController.updateFireStation(fire1)).thenReturn(rep1);
		assertNotNull(rep1);
		assertNotNull(fire1);
		assertNotNull((fireStationController).updateFireStation(fire1));

	}

	@Test
	@Tag("DELETE-FireStation")
	public void givenValidIdKeyId_whenDeleteRequest_thenReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
				.param("address", "29 15th St")
				.param("station", "2"))
				.andExpect(status().isOk());

		verify(fireStationService).deleteFireStation(anyString(), anyInt());
	}

	@Test
	@Tag("GET-Firestation")
	public void givenValidIdParam_whenGetRequest_thenReturnOkStatus() throws Exception {
		when(fireStationService.getFireStationByKeyId("1509 Culver St", 3)).thenReturn(fire);
		mockMvc.perform(MockMvcRequestBuilders.get("/firestation")
				.param("address", "1509 Culver St")
				.param("station", "3"))
				.andExpect(status().isOk());

	}

	@Test
	public void getFireStationTest() throws Exception {
		fire1 = new FireStation("29 15th St", 2);
		rep1 = new ResponseEntity<>(fire, HttpStatus.OK);

		when(fireStationController.getFireStation("29 15th St", 2)).thenReturn(rep1);
		assertNotNull(rep1);
		assertNotNull(fire1);

	}
}
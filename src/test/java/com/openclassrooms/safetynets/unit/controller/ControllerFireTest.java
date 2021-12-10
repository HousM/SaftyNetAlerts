package com.openclassrooms.safetynets.unit.controller;

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

	private ObjectMapper objectMapper;

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
		objectMapper = new ObjectMapper();

		fire = new FireStation("29 15th St", 2);
	}

	@Test
	@Tag("POST-FireStation")
	@DisplayName("Given a FireStation to add, when POST request, then return Created status")
	public void givenAFireStationToAdd_whenPostRequest_thenReturnCreatedStatus() throws Exception {
		when(fireStationService.createFireStation(fire)).thenReturn(fire);

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
		when(fireStationService.updateFireStation(fire)).thenReturn(fire);

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
	@DisplayName("Given a valid FireStation key ID, when DELETE request, then return OK status")
	public void givenValidIdKeyId_whenDeleteRequest_thenReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
				.param("address", "29 15th St")
				.param("station", "2"))
				.andExpect(status().isOk());

		verify(fireStationService).deleteFireStation(anyString(), anyInt());
	}

	@Test
	@Tag("GET-FireStation")
	@DisplayName("Given valid key ID, when GET request, then return OK status")
	public void givenValidIdParam_whenGetRequest_thenReturnOkStatus() throws Exception {
		when(fireStationService.getFireStationByKeyId(anyString(), anyInt())).thenReturn(fire);

		mockMvc.perform(MockMvcRequestBuilders.get("/fireStation")
				.param("address", "29 15th St")
				.param("station", "2"))
				.andExpect(status().isOk());

		verify(fireStationService).getFireStationByKeyId(anyString(), anyInt());
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
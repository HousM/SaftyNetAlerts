package com.openclassrooms.safetynets.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.controller.AlertsController;
import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.service.AlertsService;

@RunWith(SpringRunner.class)
@WebMvcTest(AlertsController.class)
public class ControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlertsService alertsService;

	@Before
	public void setUp() {
		@SuppressWarnings("unused")
		ObjectMapper objectMapper = new ObjectMapper();
	}

	@Test
	@Tag("GET-PersonsByStation")
	@DisplayName("Given a valid station number, when PersonsByStation request, then return Ok status")
	public void givenAValidStationNumber_whenPersonsByStationRequest_thenReturnOKStatus() throws Exception {
		when(alertsService.getPersonsByStation(anyInt())).thenReturn(any(PersonDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.get("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("stationNumber", "1"))
				.andExpect(status().isOk());

		verify(alertsService).getPersonsByStation(anyInt());
	}

	@Test
	@Tag("GET-PersonsByStation")
	@DisplayName("Given an empty station number, when PersonsByStation request, then return BadRequest status")
	public void givenAnEmptyStationNumber_whenPersonsByStationRequest_thenReturnBadRequestStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("stationNumber", ""))
				.andExpect(status().isBadRequest());

		verify(alertsService, times(0)).getPersonsByStation(anyInt());
	}

	@Test
	@Tag("GET-Fire")
	@DisplayName("Given an address, when Fire request, then return Ok status")
	public void givenAValidAddress_whenFireRequest_thenReturnOKStatus() throws Exception {
		when(alertsService.getPersonsByAddress(toString())).thenReturn(any(FireDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.get("/fire")
				.contentType(MediaType.APPLICATION_JSON)
				.param("address", "29 15th St"))
				.andExpect(status().isOk());

		verify(alertsService).getPersonsByAddress(toString());
	}
}

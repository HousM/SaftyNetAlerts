package com.openclassrooms.safetynets.unit.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.controller.MedicalRecordController;
import com.openclassrooms.safetynets.alerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
public class ControllerMedicalRecordTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordService medicalRecordService;

	private ObjectMapper objectMapper;

	private MedicalRecord med;
	private MedicalRecordDTO medDTO;

	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();

		medDTO = new MedicalRecordDTO("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		med = new MedicalRecord("Tony", "Cooper", "04/20/2002",
				Arrays.asList("ibupurin:200mg"), Arrays.asList("peanut"));

	}

	@Test
	@Tag("POST-MedicalRecord")
	@DisplayName("Given a MedicalRecord, when POST request, then return Created status")
	public void givenAMedicalRecord_whenPostRequest_thenReturnCreatedStatus() throws Exception {
		when(medicalRecordService.saveMedicalRecord(medDTO))
				.thenReturn(medDTO);

	}

	@Test
	@Tag("PUT-MedicalRecord")
	@DisplayName("Given a MedicalRecord to update, when PUT request, then return Ok status")
	public void givenAMedicalRecordToUpdate_whenPutRequest_thenReturnOkStatus() throws Exception {
		when(medicalRecordService.updateMedicalRecord(medDTO))
				.thenReturn(medDTO);

	}

	@Test
	@Tag("DELETE-MedicalRecord")
	@DisplayName("Given valid Id param, when DELETE request, then return OK status")
	public void givenValidIdParam_whenDeleteRequest_thenReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
				.andExpect(status().isOk());

		verify(medicalRecordService).deleteMedicalRecord(anyString(), anyString());
	}

	@Test
	@Tag("GET-MedicalRecord")
	@DisplayName("Given valid Id param, when GET request, then return OK status")
	public void givenValidIdParam_whenGetRequest_thenReturnOkStatus() throws Exception {
		when(medicalRecordService.getMedicalRecordById(anyString(), anyString())).thenReturn(medDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/medicalRecord")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
				.andExpect(status().isOk());

		verify(medicalRecordService).getMedicalRecordById(anyString(), anyString());
	}
}

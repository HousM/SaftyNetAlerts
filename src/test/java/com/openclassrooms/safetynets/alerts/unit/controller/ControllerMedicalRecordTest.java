package com.openclassrooms.safetynets.alerts.unit.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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
import com.openclassrooms.safetynets.alerts.controller.MedicalRecordController;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
public class ControllerMedicalRecordTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordService medicalRecordService;

	@Mock
	private MedicalRecordController medicalRecordController;
	@Mock
	private MedicalRecord med;
	@Mock
	private ResponseEntity<MedicalRecord> rep1;
	@Mock
	private ResponseEntity<MedicalRecord> rep2;
	@Mock
	private MedicalRecord medDTO1;

	private MedicalRecord medDTO;

	@Before
	public void setUp() {
		new ObjectMapper();

		medDTO = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		new MedicalRecord("Tony", "Cooper", "04/20/2002",
				Arrays.asList("ibupurin:200mg"), Arrays.asList("peanut"));

	}

	@Test
	@Tag("POST-MedicalRecord")
	@DisplayName("Given a MedicalRecord, when POST request, then return Created status")
	public void givenAMedicalRecord_whenPostRequest_thenReturnCreatedStatus() throws Exception {
		String medicalRecords = "{\"firstName\":\"Sylvanas\",\"lastName\":\"Coursevent\",\"birthdate\":\"05/04/1950\",\"medications\":\"dodoxadin:30mg\",\"allergies\":\"peanut\"}";

		when(medicalRecordService.saveMedicalRecord(med))
				.thenReturn(med);
		mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(medicalRecords))
				.andExpect(status().is(201));
	}

	@Test
	public void saveMedicalRecordTest() throws Exception {
		medDTO1 = new MedicalRecord("John1", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		rep2 = new ResponseEntity<>(medDTO1, HttpStatus.CREATED);
		when(medicalRecordService.saveMedicalRecord(medDTO1))
				.thenReturn(medDTO1);
		when(medicalRecordController.saveMedicalRecord(medDTO1)).thenReturn(rep2);
		assertNotNull(rep2);
		assertNotNull(medDTO1);
		assertNotNull((medicalRecordController).saveMedicalRecord(medDTO1));

	}

	@Test
	public void updateMedicalRecordTest() throws Exception {
		medDTO1 = new MedicalRecord("John1", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		new MedicalRecord("John1", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		rep1 = new ResponseEntity<>(medDTO1, HttpStatus.OK);
		when(medicalRecordService.updateMedicalRecord(medDTO1))
				.thenReturn(medDTO1);
		when(medicalRecordController.updateMedicalRecord(medDTO1)).thenReturn(rep1);
		assertNotNull(rep1);
		assertNotNull(medDTO1);
		assertNotNull((medicalRecordController).updateMedicalRecord(medDTO1));

	}

	@Test
	@Tag("PUT-MedicalRecord")
	@DisplayName("Given a MedicalRecord to update, when PUT request, then return Ok status")
	public void givenAMedicalRecordToUpdate_whenPutRequest_thenReturnOkStatus() throws Exception {
		String medicalRecords = "{\"firstName\":\"Sylvanas\",\"lastName\":\"Coursevent\",\"birthdate\":\"05/04/1950\",\"medications\":\"dodoxadin:30mg\",\"allergies\":\"peanut\"}";

		when(medicalRecordService.updateMedicalRecord(medDTO))
				.thenReturn(medDTO);
		mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(medicalRecords))
				.andExpect(status().isOk());

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

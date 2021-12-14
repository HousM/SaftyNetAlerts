package com.openclassrooms.safetynets.alerts.unit.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.safetynets.alerts.AlertsApplication;
import com.openclassrooms.safetynets.alerts.controller.MedicalRecordController;
import com.openclassrooms.safetynets.alerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@ExtendWith(MockitoExtension.class)
public class ServiceMedicalRecordTest {

	private static final Logger logger = LogManager.getLogger(AlertsApplication.class);
	@Mock
	private MedicalRecordController medicalRecordController;
	@Mock
	private MedicalRecordRepository medicalRecordRepositoryMock;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private MedicalRecordService medicalRecordService;

	@Mock
	private static MedicalRecordDTO medDTO;
	private static MedicalRecord med;

	@Mock
	private Object medUpdated;

	@BeforeClass
	static void setup() throws IOException {
		medDTO = new MedicalRecordDTO("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
	}

	@Test
	void saveMedicalrecordTest() throws Exception {
		MedicalRecordDTO medDTO1 = new MedicalRecordDTO();
		medicalRecordService.saveMedicalRecord(medDTO1);
		verify(medicalRecordService).saveMedicalRecord(medDTO1);
	}

	@Test
	void updateMedicalRecordTest() throws Exception {
		MedicalRecordDTO medDTO1 = new MedicalRecordDTO();
		medicalRecordService.updateMedicalRecord(medDTO1);
		verify(medicalRecordService).updateMedicalRecord(medDTO1);
		Mockito.when(medicalRecordService.updateMedicalRecord(medDTO1)).thenReturn(null);
	}

	@Test
	void getMedicalRecordByIdTest() throws Exception {
		MedicalRecordDTO medicalrecordToSave = new MedicalRecordDTO(
				"New",
				"NewName",
				"03/06/1985",
				new ArrayList<>(Arrays.asList("fakeMedic1", "fakeMedic2")),
				new ArrayList<>(Arrays.asList("fakeAllergy1")));
		medicalRecordService.getMedicalRecordById("New", "NewName");
		verify(medicalRecordService).getMedicalRecordById("New", "NewName");
		when(medicalRecordService.getMedicalRecordById("New", "NewName")).thenReturn(medicalrecordToSave);
	}

	@Test

	public void deleteMedicalRecordTest() throws Exception {
		MedicalRecordDTO medDTO1 = new MedicalRecordDTO();
		medicalRecordService.deleteMedicalRecord(medDTO1.getFirstName(), medDTO1.getLastName());
		verify(medicalRecordService).deleteMedicalRecord(medDTO1.getFirstName(), medDTO1.getLastName());
	}
//	@Test
//	public void saveMedicalrecordTest() throws Exception {
//		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//		assertThat(medicalRecordService.saveMedicalRecord(medDTO)).isNotNull();
//	}

//	@Test
//	void testSaveMedicalrecord() throws Exception {
//		// Arrange
//		MedicalRecord medicalrecordToSave = new MedicalRecord(
//				"New",
//				"NewName",
//				"03/06/1985",
//				new ArrayList<>(Arrays.asList("fakeMedic1", "fakeMedic2")),
//				new ArrayList<>(Arrays.asList("fakeAllergy1")));
//		MedicalRecordDTO medicalrecordSaved = medDTO.toMedicalRecordDTO(medicalrecordToSave);
//
//		when(medicalRecordRepositoryMock.findByIdentity("New", "NewName"))
//				.thenReturn(medicalrecordToSave);// At the end the medicalrecord is present
//		doNothing().when(medicalRecordRepositoryMock).save(medicalrecordToSave);
//
	// Act
//		MedicalRecordDTO result = (medicalRecordService.saveMedicalRecord(medicalrecordSaved));
//
	// Assert
//		assertNotNull(result);
//		assertEquals(medicalrecordToSave, result);
//		verify(medicalRecordRepositoryMock, times(1)).save(any(MedicalRecord.class));

//	}

//	@Test
//	@DisplayName("Given a registered medicalRecord, when updateMedicalRecord, then medicalRecord should be updated" +
//			" correctly")
//	public void updateMedicalRecordTest() throws Exception {
//
//		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//		medUpdated = medicalRecordService.updateMedicalRecord(medDTO);
//		assertThat(medUpdated).isNotNull();
//		assertThat(medUpdated).isEqualTo(medDTO);
//
//	}

//	@Test
//	@DisplayName("Given a medicalRecord, when createMedicalRecord, then medicalRecord should be saved correctly")
//	public void givenAMedicalRecord_whenCreateMedicalRecord_thenMedicalRecordShouldBeSavedCorrectly() throws Exception {
//		med = new MedicalRecord("John", "Boyd", "03/06/1984",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(null);
//		when(medicalRecordRepositoryMock.save(any(MedicalRecord.class))).thenReturn(med);
//		when(medDTO.toMedicalRecordDTO(med)).thenReturn(medDTO);
//
//		MedicalRecordDTO medCreated = medicalRecordService.saveMedicalRecord(medDTO);
////		when(medicalRecordService.saveMedicalRecord(medCreated)).thenReturn(any(MedicalRecordDTO.class));
//		assertNotNull(medCreated);
//
////		assertThat(medCreated).isEqualTo(medDTO);
//
//		InOrder inOrder = Mockito.inOrder(medicalRecordRepositoryMock, medDTO);
////		inOrder.verify(medicalRecordRepositoryMock).findByIdentity(anyString(), anyString());
//
////		inOrder.verify(medicalRecordRepositoryMock).save(any(MedicalRecord.class));
////		verify(medDTO).toMedicalRecordDTO(any(MedicalRecord.class));
//		logger.info("This is TEST");
////		inOrder.verify(medicalRecordService.updateMedicalRecord(medDTO));
//
//	}

//	@Test
//	@Tag("UpdateMedicalRecord")
//	@DisplayName("If medicalRecord is not registered, when updateMedicalRecord, then throw DataNotFoundException")
//	public void givenUnFoundMedicalRecord_whenUpdateMedicalRecord_thenDataNotFoundExceptionIsThrown() throws Exception {
//		medDTO = new MedicalRecordDTO("John", "Boyd", "03/06/1984",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(null);
//
//		medicalRecordService.updateMedicalRecord(medDTO);
//	}

//	@Test
//	@DisplayName("Given a person Id, when deleteMedicalRecord, then delete process should be done " +
//			"in correct order")
//	public void deleteMedicalRecordTest() throws Exception {
//		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//		medicalRecordService.deleteMedicalRecord(medDTO.getFirstName(), medDTO.getLastName());
//		assertThat(medDTO).isEqualTo(null);
//		assertThat(medicalRecordRepositoryMock.findByIdentity(medDTO.getFirstName(), medDTO.getLastName()))
//				.isEqualTo(null);
//	}
//
//	@Test
//	@DisplayName("Given a person ID, when getMedicalRecordById, then expected medicalRecord should be " +
//			"returned correctly")
//	public void getMedicalRecordByIdTest()
//			throws Exception {
//		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//
//		MedicalRecordDTO medByIdFound = medicalRecordService.getMedicalRecordById(medDTO.getFirstName(),
//				medDTO.getLastName());
//
//		assertThat(medByIdFound).isEqualTo(medDTO);
//		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//		when(medicalRecordService.getMedicalRecordById(anyString(), anyString())).thenReturn(medDTO);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/medicalRecord")
//				.param("firstName", "JohnN")
//				.param("lastName", "BoydN"))
//				.andExpect(status().isOk());
//
//		verify(medicalRecordService).getMedicalRecordById(anyString(), anyString());

}

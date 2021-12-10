package com.openclassrooms.safetynets.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetynets.alerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@ExtendWith(MockitoExtension.class)
public class ServiceMedicalRecordTest {
	@InjectMocks
	private MedicalRecordRepository medicalRecordRepositoryMock;

	private MedicalRecordService medicalRecordService;

	@Mock
	private MedicalRecordDTO medDTO;

	@Mock
	private Object medUpdated;

	@Test
	public void saveMedicalrecordTest() throws Exception {
		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		assertThat(medicalRecordService.saveMedicalRecord(medDTO)).isNotNull();
	}

	@Test
	@DisplayName("Given a registered medicalRecord, when updateMedicalRecord, then medicalRecord should be updated" +
			" correctly")
	public void updateMedicalRecordTest() throws Exception {

		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		medUpdated = medicalRecordService.updateMedicalRecord(medDTO);
		assertThat(medicalRecordService.updateMedicalRecord(medDTO)).isNotNull();
		assertThat(medUpdated).isEqualTo(medDTO);

	}

	@Test
	@DisplayName("Given a person Id, when deleteMedicalRecord, then delete process should be done " +
			"in correct order")
	public void deleteMedicalRecordTest() throws Exception {
		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		medicalRecordService.deleteMedicalRecord(medDTO.getFirstName(), medDTO.getLastName());
		assertThat(medDTO).isEqualTo(null);
		assertThat(medicalRecordRepositoryMock.findByIdentity(medDTO.getFirstName(), medDTO.getLastName()))
				.isEqualTo(null);
	}

	@Test
	@DisplayName("Given a person ID, when getMedicalRecordById, then expected medicalRecord should be " +
			"returned correctly")
	public void getMedicalRecordByIdTest()
			throws Exception {
		medDTO = new MedicalRecordDTO("JohnN", "BoydN", "03/06/1985",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

		MedicalRecordDTO medByIdFound = medicalRecordService.getMedicalRecordById(medDTO.getFirstName(),
				medDTO.getLastName());

		assertThat(medByIdFound).isEqualTo(medDTO);

	}
}

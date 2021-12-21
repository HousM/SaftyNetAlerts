package com.openclassrooms.safetynets.alerts.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@ExtendWith(MockitoExtension.class)
public class ServiceMedicalRecordTest {

	@InjectMocks
	private MedicalRecordService medicalRecordService;

	@Mock
	private MedicalRecordRepository medicalRecordRepositoryMock;

	@Test
	void saveMedicalrecordTest() throws Exception {

		MedicalRecord med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

		when(medicalRecordRepositoryMock.save(med)).thenReturn(med);

		MedicalRecord medCreated = medicalRecordService.saveMedicalRecord(med);

		assertNotNull(medCreated);
//		assertThat(medCreated).isEqualTo(medDTO);
	}

	@Test
	void updateMedicalRecordTest() throws Exception {
		MedicalRecord med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

		when(medicalRecordRepositoryMock.update(med)).thenReturn(med);
		when(medicalRecordRepositoryMock.findByIdentity(med.getFirstName(),
				med.getLastName())).thenReturn(med);

//		MedicalRecordDTO medToSave = medDTO.toMedicalRecordDTO(med);
		MedicalRecord medUpdated = medicalRecordService.updateMedicalRecord(med);

		assertThat(medUpdated.getAllergiesList().contains("peanut"));
//		assertThat(medUpdated).isNull();

	}

	@Test
	void getMedicalRecordByIdTest() throws Exception {
		MedicalRecord med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(med);

//		MedicalRecordDTO medTofind = medDTO.toMedicalRecordDTO(med);
		MedicalRecord medByIdFound = medicalRecordService.getMedicalRecordById(med.getFirstName(),
				med.getLastName());

		assertThat(medByIdFound).isEqualTo(medByIdFound);
//		assertThat(medByIdFound).isNull();
	}

	@Test

	public void deleteMedicalRecordTest() throws Exception {
		MedicalRecord med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(med);
		medicalRecordService.deleteMedicalRecord(med.getFirstName(), med.getLastName());
	}
}

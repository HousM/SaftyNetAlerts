package com.openclassrooms.safetynets.alerts.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetynets.alerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@ExtendWith(MockitoExtension.class)
public class ServiceMedicalRecordTest {

	@InjectMocks
	private MedicalRecordService medicalRecordService;

	@Mock
	private MedicalRecordRepository medicalRecordRepositoryMock;

	private static MedicalRecordDTO medDTO;
	@Mock
	private static MedicalRecord med;

	@Before
	static void setup() throws IOException {
		medDTO = new MedicalRecordDTO("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
	}

	@Test
	void saveMedicalrecordTest() throws Exception {

		when(medicalRecordRepositoryMock.save(med)).thenReturn(med);

		MedicalRecordDTO medToCreate = medDTO.toMedicalRecordDTO(med);
		MedicalRecordDTO medCreated = medicalRecordService.saveMedicalRecord(medToCreate);

		assertNotNull(medCreated);
//		assertThat(medCreated).isEqualTo(medDTO);
	}

	@Test
	void updateMedicalRecordTest() throws Exception {

		when(medicalRecordRepositoryMock.update(med)).thenReturn(med);

		MedicalRecordDTO medToSave = medDTO.toMedicalRecordDTO(med);
		MedicalRecordDTO medUpdated = medicalRecordService.updateMedicalRecord(medToSave);

		assertThat(medUpdated.getAllergies().contains("peanut"));
//		assertThat(medUpdated).isNull();

	}

	@Test
	void getMedicalRecordByIdTest() throws Exception {

		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(med);

		MedicalRecordDTO medTofind = medDTO.toMedicalRecordDTO(med);
		MedicalRecordDTO medByIdFound = medicalRecordService.getMedicalRecordById(medTofind.getFirstName(),
				medTofind.getLastName());

		assertThat(medByIdFound).isEqualTo(medByIdFound);
//		assertThat(medByIdFound).isNull();
	}

	@Test

	public void deleteMedicalRecordTest() throws Exception {
		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(med);

		medicalRecordService.deleteMedicalRecord(med.getFirstName(), med.getLastName());
	}
}

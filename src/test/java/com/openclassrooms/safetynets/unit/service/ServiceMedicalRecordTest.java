package com.openclassrooms.safetynets.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.safetynets.alerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@RunWith(MockitoJUnitRunner.class)
public class ServiceMedicalRecordTest {
	@Mock
	private MedicalRecordRepository medicalRecordRepositoryMock;

	@InjectMocks
	private MedicalRecordService medicalRecordService;

	private MedicalRecord med;
	private MedicalRecordDTO medDTO;

	@Before
	public void setUp() {
		med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		medDTO = new MedicalRecordDTO("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
	}

	@Test
	@DisplayName("Update Medicalrecord: success case")
	public void testSaveMedicalrecord() throws Exception {

		when(medicalRecordRepositoryMock.findByIdentity("New", "NewName")).thenReturn(med);

	}

	@Test
	@Tag("UpdateMedicalRecord")
	@DisplayName("Given a registered medicalRecord, when updateMedicalRecord, then medicalRecord should be updated" +
			" correctly")
	public void testCreateMedicalrecord() {
		MedicalRecord medicalrecordTocreate = new MedicalRecord("New", "NewName", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

		when(medicalRecordRepositoryMock.findByIdentity("New", "NewName")).thenReturn(medicalrecordTocreate);

	}

	@Test
	@Tag("DeleteMedicalRecord")
	@DisplayName("Given a person Id, when deleteMedicalRecord, then delete process should be done " +
			"in correct order")
	public void givenValidId_whenDeleteMedicalRecord_thenDeletingShouldBeDoneInCorrectOrder() throws Exception {
		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(med);

		medicalRecordService.deleteMedicalRecord(med.getFirstName(), med.getLastName());

		verify(medicalRecordRepositoryMock).findByIdentity(anyString(), anyString());
		verify(medicalRecordRepositoryMock).delete(any(MedicalRecord.class));
	}

	@Test
	@Tag("GetMedicalRecordById")
	@DisplayName("Given a person ID, when getMedicalRecordById, then expected medicalRecord should be " +
			"returned correctly")
	public void givenAMedicalRecordById_whenGetMedicalRecordById_thenExpectedMedicalRecordShouldBeReturnCorrectly()
			throws Exception {
		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(med);
		when(medDTO.toMedicalRecordDTO(any(MedicalRecord.class))).thenReturn(medDTO);

		MedicalRecordDTO medByIdFound = medicalRecordService.getMedicalRecordById(medDTO.getFirstName(),
				medDTO.getLastName());

		assertThat(medByIdFound).isEqualTo(medDTO);

		verify(medicalRecordRepositoryMock).findByIdentity(anyString(), anyString());

	}
}

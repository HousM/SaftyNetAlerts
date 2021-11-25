package com.openclassrooms.safetynets.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@RunWith(MockitoJUnitRunner.class)
public class MedicalRecordServiceTest {
	@Mock
	private MedicalRecordRepository medicalRecordRepositoryMock;

	@InjectMocks
	private MedicalRecordService medicalRecordService;

	private static MedicalRecord med;
	private MedicalRecordRepository medicalRecordRepository;

	@Before
	public void setUp() {
		med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));

	}

	@Test
	@Tag("CreateMedicalRecord")
	@DisplayName("Given a medicalRecord, when createMedicalRecord, then medicalRecord should be saved correctly")
	public void givenAMedicalRecord_whenCreateMedicalRecord_thenMedicalRecordShouldBeSavedCorrectly() throws Exception {
		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(null);
		when(medicalRecordRepository.findByIdentity("John", "Boyd")).thenReturn(med);
		when(medicalRecordRepositoryMock.save(any(MedicalRecord.class))).thenReturn(med);

		MedicalRecord medCreated = medicalRecordService.createMedicalRecord(med);

		assertNotNull(medCreated);
		assertThat(medCreated).isEqualTo(med);

		verify(medicalRecordRepositoryMock).findByIdentity(anyString(), anyString());
		verify(medicalRecordRepository.findByIdentity(medCreated.getFirstName(), medCreated.getLastName()));
		verify(medicalRecordRepositoryMock).save(any(MedicalRecord.class));

	}

	@Test
	@Tag("UpdateMedicalRecord")
	@DisplayName("Given a registered medicalRecord, when updateMedicalRecord, then medicalRecord should be updated" +
			" correctly")
	public void givenAMedicalRecord_whenUpdateMedicalRecord_thenMedicalRecordShouldBeUpdateCorrectly()
			throws Exception {
		med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan", "peanut"));
		when(medicalRecordRepositoryMock.findByIdentity(anyString(), anyString())).thenReturn(med);
		when(medicalRecordRepository.findByIdentity("John", "Boyd")).thenReturn(med);

		MedicalRecord medUpdated = medicalRecordService.updateMedicalRecord(med);

		assertThat(medUpdated.getAllergiesList().contains("peanut"));

		verify(medicalRecordRepositoryMock).findByIdentity(anyString(), anyString());
		verify(medicalRecordRepository.findByIdentity(medUpdated.getFirstName(), medUpdated.getLastName()));
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
		when(medicalRecordRepository.findByIdentity("John", "Boyd")).thenReturn(med);

		MedicalRecord medByIdFound = medicalRecordService.getMedicalRecordById(med.getFirstName(),
				med.getLastName());

		assertThat(medByIdFound).isEqualTo(med);

		verify(medicalRecordRepositoryMock).findByIdentity(anyString(), anyString());
		verify(medicalRecordRepository.findByIdentity(medByIdFound.getFirstName(), medByIdFound.getLastName()));
	}
}

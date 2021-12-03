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

	@Before
	public void setUp() {
		med = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
	}
//
//	@Test
//	@DisplayName("Update Medicalrecord: success case")
//	public void testSaveMedicalrecord() throws Exception {
//		// Arrange
//		MedicalRecord medicalrecordToSave = new MedicalRecord("New", "NewName", "03/06/1984",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//		MedicalRecord medicalrecordExpected = new MedicalRecord("New", "NewName",
//				"03/06/1984",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//
//		when(medicalRecordRepositoryMock.findByIdentity("New", "NewName")).thenReturn(medicalrecordToSave);
//		// Act
//		MedicalRecord medSaved = medicalRecordService.updateMedicalRecord(medicalrecordToSave);
//		// Assert
//		assertNotNull(medSaved);
//		assertEquals(medicalrecordExpected, medSaved);
//
//		verify(medicalRecordRepositoryMock).save(any(MedicalRecord.class));
//
//	}

//
//	@Test
//	@Tag("UpdateMedicalRecord")
//	@DisplayName("Given a registered medicalRecord, when updateMedicalRecord, then medicalRecord should be updated" +
//			" correctly")
//	public void testCreateMedicalrecord() {
//		MedicalRecord medicalrecordTocreate = new MedicalRecord("New", "NewName", "03/06/1984",
//				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
//
//		when(medicalRecordRepositoryMock.findByIdentity("New", "NewName")).thenReturn(medicalrecordTocreate);
//		verify(medicalRecordRepositoryMock).save(any(MedicalRecord.class));
//
//	}

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

		MedicalRecord medByIdFound = medicalRecordService.getMedicalRecordById(med.getFirstName(),
				med.getLastName());

		assertThat(medByIdFound).isEqualTo(med);

		verify(medicalRecordRepositoryMock).findByIdentity(anyString(), anyString());

	}
}
